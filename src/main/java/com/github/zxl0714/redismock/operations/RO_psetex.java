package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.Utils;

import java.util.List;

class RO_psetex extends RO_setex {
    RO_psetex(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    long valueToSet(List<Slice> params){
        return Utils.convertToLong(new String(params.get(1).data()));
    }
}
