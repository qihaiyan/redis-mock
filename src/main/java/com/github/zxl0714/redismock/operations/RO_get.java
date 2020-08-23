package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_get extends AbstractRedisOperation {
    RO_get(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        return Response.bulkString(base().getValue(params().get(0)));
    }
}
