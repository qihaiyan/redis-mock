package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;
import java.util.Map;

public class RO_hlen extends AbstractRedisOperation {
    public RO_hlen(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        Map<Slice, Slice> map = base().getFieldsAndValues(key);
        return Response.integer(map.size());
    }
}
