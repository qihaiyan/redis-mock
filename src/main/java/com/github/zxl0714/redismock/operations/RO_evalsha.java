package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_evalsha extends AbstractRedisOperation {
    RO_evalsha(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        base().putValue(params().get(0), params().get(1));
        return Response.integer(1);
    }
}
