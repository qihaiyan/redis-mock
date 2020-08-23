package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.Utils;

import java.util.List;

class RO_incrbyfloat extends RO_incrOrDecrByFloat {
    RO_incrbyfloat(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    double incrementOrDecrementValue(List<Slice> params){
        return Utils.convertToDouble(String.valueOf(params.get(1)));
    }
}
