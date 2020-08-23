package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

import static com.github.zxl0714.redismock.Utils.convertToLong;

abstract class RO_incrOrDecrBy extends AbstractRedisOperation {
    RO_incrOrDecrBy(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    abstract long incrementOrDecrementValue(List<Slice> params);

    Slice response() {
        Slice key = params().get(0);
        long d = incrementOrDecrementValue(params());
        Slice v = base().getValue(key);
        if (v == null) {
            base().putValue(key, Slice.create(String.valueOf(d)));
            return Response.integer(d);
        }

        long r = convertToLong(new String(v.data())) + d;
        base().putValueWithoutClearingTtl(key, Slice.create(String.valueOf(r)));
        return Response.integer(r);
    }
}
