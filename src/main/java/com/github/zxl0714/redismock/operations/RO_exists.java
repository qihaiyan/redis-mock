package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_exists extends AbstractRedisOperation {
    RO_exists(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        if (base().exists(params().get(0))) {
            return Response.integer(1);
        }
        return Response.integer(0);
    }
}
