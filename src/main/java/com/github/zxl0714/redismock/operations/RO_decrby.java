package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;

import java.util.List;

import static com.github.zxl0714.redismock.Utils.convertToLong;

class RO_decrby extends RO_incrOrDecrBy {
    RO_decrby(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    long incrementOrDecrementValue(List<Slice> params){
        return convertToLong(String.valueOf(params.get(1))) * -1;
    }
}
