package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_blpop extends RO_bpop {
    RO_blpop(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    RO_pop popper(List<Slice> params) {
        return new RO_lpop(base(), params);
    }
}
