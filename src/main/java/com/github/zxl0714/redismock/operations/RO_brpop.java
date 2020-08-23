package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

class RO_brpop extends RO_bpop {

    RO_brpop(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    RO_pop popper(List<Slice> params) {
        return new RO_rpop(base(), params);
    }
}
