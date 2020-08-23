package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.Utils;

import java.util.List;

class RO_incrby extends RO_incrOrDecrBy {
    RO_incrby(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    long incrementOrDecrementValue(List<Slice> params){
        return Utils.convertToLong(String.valueOf(params.get(1)));
    }
}
