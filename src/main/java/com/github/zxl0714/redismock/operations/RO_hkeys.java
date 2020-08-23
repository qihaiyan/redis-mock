package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RO_hkeys extends AbstractRedisOperation {
    public RO_hkeys(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice hash = params().get(0);

        Map<Slice, Slice> fieldAndValueMap = base().getFieldsAndValues(hash);
        int arraySize = fieldAndValueMap.size();
        Slice [] fkeys = new Slice[arraySize];

        int currentIndex = 0;
        for (Map.Entry<Slice, Slice> entry: fieldAndValueMap.entrySet()){
            fkeys[currentIndex] = Response.bulkString(entry.getKey());
            currentIndex++;
        }

        List<Slice> values = Arrays.asList(fkeys);
        return Response.array(values);
    }
}
