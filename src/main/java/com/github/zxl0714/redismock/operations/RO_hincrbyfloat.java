package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.Utils;

import java.util.List;

class RO_hincrbyfloat extends RO_hincrby {
    RO_hincrbyfloat(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice hsetValue(Slice key1, Slice key2, Slice value) {
        double numericValue = Utils.convertToDouble(String.valueOf(value));
        Slice foundValue = base().getValue(key1, key2);
        if (foundValue != null) {
            numericValue = Utils.convertToDouble(new String(foundValue.data())) + numericValue;
        }
        Slice res = Slice.create(String.valueOf(numericValue));
        base().putValue(key1, key2, res, -1L);

        return Response.bulkString(res);
    }
}
