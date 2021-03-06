package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_flushdb extends AbstractRedisOperation {
    RO_flushdb(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        base().clear();
        return Response.OK;
    }
}
