package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.server.SliceParser;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.zxl0714.redismock.Utils.convertToLong;

abstract class RO_bpop extends AbstractRedisOperation {

    private Slice source;

    RO_bpop(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    abstract RO_pop popper(List<Slice> params);

    void doOptionalWork() {
        source = null;
        int size = params().size();
        if (size < 2) {
            throw new IndexOutOfBoundsException("require at least 2 params");
        }
        List<Slice> keys = params().subList(0, size - 1);
        long timeout = convertToLong(params().get(size - 1).toString());
        long currentSleep = 0L;
        while (source == null && currentSleep < timeout * 1000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentSleep += 100;
            source = getKey(keys);
        }
    }

    Slice response() {
        if (source != null) {
            Slice result = popper(Collections.singletonList(source)).execute();
            return Response.array(Arrays.asList(Response.bulkString(source), result));
        } else {
            return Response.NULL;
            //return Response.array(Collections.emptyList());
        }
    }

    private Slice getKey(List<Slice> list) {
        for (Slice key : list) {
            Slice result = new RO_llen(base(), Collections.singletonList(key)).execute();
            int length = SliceParser.consumeInteger(result.data());
            if (length > 0) {
                return key;
            }
        }
        return null;
    }
}
