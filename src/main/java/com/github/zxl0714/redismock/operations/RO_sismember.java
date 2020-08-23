package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;
import java.util.Set;

public class RO_sismember extends AbstractRedisOperation {

    RO_sismember(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Slice member = params().get(1);
        Set<Slice> set = getDataFromBase(key, null);
        if (set == null || set.isEmpty()) return Response.integer(0);
        return Response.integer(set.contains(member) ? 1 : 0);
    }
}
