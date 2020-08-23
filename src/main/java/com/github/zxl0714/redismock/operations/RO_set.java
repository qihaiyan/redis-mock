package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_set extends AbstractRedisOperation {
    RO_set(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        if (params().size() == 5) {
            if (base().getValue(params().get(0)) != null) {
                return Response.NULL;
            }
        }
        base().putValue(params().get(0), params().get(1));
        return Response.OK;
    }
}
