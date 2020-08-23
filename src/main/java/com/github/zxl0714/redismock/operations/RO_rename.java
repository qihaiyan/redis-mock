package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;
import java.util.Map;

class RO_rename extends AbstractRedisOperation {

    RO_rename(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    private boolean rename(Slice key, Slice newKey) {
        Map<Slice, Slice> value = base().getFieldsAndValues(key);
        final Long ttl = base().getTTL(key);
        if (ttl == null) {
            return false;
        }
        for (Map.Entry<Slice, Slice> entry : value.entrySet()) {
            base().putValue(newKey, entry.getKey(), entry.getValue(), ttl);
        }
        base().deleteValue(key);
        return true;
    }

    @Override
    Slice response() {
        final Slice key = params().get(0);
        final Slice newKey = params().get(1);
        if (!rename(key, newKey)) {
            return Response.error("ERR no such key");
        }
        return Response.OK;
    }
}
