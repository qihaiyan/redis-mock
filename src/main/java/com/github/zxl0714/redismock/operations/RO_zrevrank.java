package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class RO_zrevrank extends AbstractRedisOperation {

    private static final String WITH_SCORES = "WITHSCORES";

    RO_zrevrank(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Map<Slice, Double> map = getDataFromBase(key, new LinkedHashMap<>());

        String value = params().get(1).toString();

        if (map == null || map.isEmpty()) return Response.integer(0);
        int rank = 0;
        for (int i = params().size() - 1; i > 0; i--) {
            if (params().get(i).toString().equals(value)) {
                rank++;
            }
        }

        return Response.integer(rank);
    }
}
