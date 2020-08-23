package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;

import java.util.List;

class RO_lpushx extends RO_lpush {
    RO_lpushx(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        Slice key = params().get(0);
        Slice data = base().getValue(key);

        if(data != null){
            return super.response();
        }

        return Response.integer(0);
    }
}
