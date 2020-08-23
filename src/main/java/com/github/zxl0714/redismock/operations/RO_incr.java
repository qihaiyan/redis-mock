package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;

import java.util.List;

class RO_incr extends RO_incrby {
    RO_incr(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    long incrementOrDecrementValue(List<Slice> params){
        return 1L;
    }
}
