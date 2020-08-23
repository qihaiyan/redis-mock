package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;
import java.util.Map;

import static com.github.zxl0714.redismock.Utils.serializeObject;

class RO_zrem extends AbstractRedisOperation {

    RO_zrem(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        Map<Slice, Double> map = getDataFromBase(key, null);
        if(map == null || map.isEmpty()) return Response.integer(0);
        int count = 0;
        for (int i = 1; i < params().size(); i++) {
            if (map.remove(params().get(i)) != null) {
                count++;
            }
        }
        try {
            base().putValue(key, serializeObject(map));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return Response.integer(count);
    }
}
