package com.github.zxl0714.redismock.operations;

import com.google.common.collect.Lists;
import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.zxl0714.redismock.Utils.convertToInteger;

class RO_zrevrange extends AbstractRedisOperation {

    private static final String WITH_SCORES = "WITHSCORES";

    RO_zrevrange(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Map<Slice, Double> map = getDataFromBase(key, new LinkedHashMap<>());

        int start = convertToInteger(params().get(1).toString());
        int end = convertToInteger(params().get(2).toString());

        if (start < 0) {
            start = map.size() + start;
            if (start < 0) {
                start = 0;
            }
        }

        if (end < 0) {
            end = map.size() + end;
            if (end < 0) {
                end = -1;
            }
        }

        if (end >= map.size()) {
            end = map.size() - 1;
        }

        final boolean withScores = params().size() == 4 && WITH_SCORES.equalsIgnoreCase(params().get(3).toString());

        List<Slice> values = map.entrySet().stream()
            .skip(start)
            .limit(end - start + 1)
            .flatMap(e -> withScores
                    ? Lists.newArrayList(e.getKey(), Slice.create(e.getValue().toString())).stream()
                    : Lists.newArrayList(e.getKey()).stream())
            .map(Response::bulkString)
            .collect(Collectors.toList());

        return Response.array(values);
    }
}
