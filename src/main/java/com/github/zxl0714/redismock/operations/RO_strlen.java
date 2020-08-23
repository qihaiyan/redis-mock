package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_strlen extends AbstractRedisOperation {
    RO_strlen(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice value = base().getValue(params().get(0));
        if (value == null) {
            return Response.integer(0);
        }
        return Response.integer(value.length());
    }
}
