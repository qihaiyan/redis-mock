package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_append extends AbstractRedisOperation {
    RO_append(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        Slice value = params().get(1);
        Slice s = base().getValue(key);
        if (s == null) {
            base().putValue(key, value);
            return Response.integer(value.length());
        }
        byte[] b = new byte[s.length() + value.length()];
        for (int i = 0; i < s.length(); i++) {
            b[i] = s.data()[i];
        }
        for (int i = s.length(); i < s.length() + value.length(); i++) {
            b[i] = value.data()[i - s.length()];
        }
        base().putValue(key, Slice.create(b));
        return Response.integer(b.length);
    }
}
