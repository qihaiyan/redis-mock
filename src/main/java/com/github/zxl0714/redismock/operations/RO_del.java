package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_del extends AbstractRedisOperation {
    RO_del(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        int count = 0;
        for (Slice key : params()) {
            if (base().exists(key)) {
                base().deleteValue(key);
                count++;
            }
        }
        return Response.integer(count);
    }
}
