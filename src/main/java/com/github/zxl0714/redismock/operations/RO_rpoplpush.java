package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.server.SliceParser;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.zxl0714.redismock.server.Response.NULL;

class RO_rpoplpush extends AbstractRedisOperation {
    RO_rpoplpush(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice source = params().get(0);
        Slice target = params().get(1);

        //Pop last one
        Slice result = new RO_rpop(base(), Collections.singletonList(source)).execute();
        if(result.equals(NULL)) return NULL;

        Slice valueToPush = SliceParser.consumeParameter(result.data());

        //Push it into the other list
        new RO_lpush(base(), Arrays.asList(target, valueToPush)).execute();

        return result;
    }
}
