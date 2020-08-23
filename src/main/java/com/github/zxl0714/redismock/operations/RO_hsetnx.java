package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_hsetnx extends RO_hset {
    RO_hsetnx(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice hsetValue(Slice key1, Slice key2, Slice value){
        Slice foundValue = base().getValue(key1, key2);
        if(foundValue == null){
            base().putValue(key1, key2, value, -1L);
            foundValue = value;
        }
        return foundValue;
    }
}
