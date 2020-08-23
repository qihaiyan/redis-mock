package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;

import java.util.List;

class RO_expire extends RO_pexpire {
    RO_expire(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    long getValue(List<Slice> params){
        return super.getValue(params) * 1000;
    }
}
