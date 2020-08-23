package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.server.SliceParser;
import com.github.zxl0714.redismock.Utils;

import java.util.Arrays;
import java.util.List;

class RO_brpoplpush extends RO_rpoplpush {
    private long count = 0L;

    RO_brpoplpush(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    void doOptionalWork(){
        Slice source = params().get(0);
        long timeout = Utils.convertToLong(params().get(2).toString());

        //TODO: Remove active block dumb.
        long currentSleep = 0L;
        while(count == 0L && currentSleep < timeout * 1000){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentSleep = currentSleep + 100;
            count = getCount(source);
        }
    }

    Slice response() {
        if(count != 0){
            return super.response();
        } else {
            return Response.NULL;
        }
    }

    private long getCount(Slice source){
        Slice index = Slice.create("0");
        List<Slice> commands = Arrays.asList(source, index, index);
        Slice result = new RO_lrange(base(), commands).execute();
        return SliceParser.consumeCount(result.data());
    }
}
