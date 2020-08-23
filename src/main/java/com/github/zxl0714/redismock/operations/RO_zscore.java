package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class RO_zscore extends AbstractRedisOperation {

    private static final String WITH_SCORES = "WITHSCORES";

    RO_zscore(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Map<Slice, Double> map = getDataFromBase(key, new LinkedHashMap<>());

        String value = params().get(1).toString();

        if (map == null || map.isEmpty()) return Response.bulkString(Response.zsetDoubleValue(0.0));

        return map.entrySet().stream()
                .filter(e -> e.getKey().toString().equals(value))
                .findAny()
                .map(sliceDoubleEntry -> Response.doubleValue(sliceDoubleEntry.getValue())).orElseGet(() -> Response.doubleValue(0.0));
    }
}
