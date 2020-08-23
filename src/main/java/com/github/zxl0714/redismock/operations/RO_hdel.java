package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_hdel extends AbstractRedisOperation {
    RO_hdel(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        Slice key1 = params().get(0);
        Slice key2 = params().get(1);

        Slice oldValue = base().getValue(key1, key2);

        base().deleteValue(key1, key2);

        if(oldValue == null){
            return Response.integer(0);
        } else {
            return Response.integer(1);
        }
    }
}
