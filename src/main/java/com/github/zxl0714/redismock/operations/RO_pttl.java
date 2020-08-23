package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;

import java.util.List;

class RO_pttl extends RO_ttl {
    RO_pttl(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice finalReturn(Long pttl){
        return Response.integer(pttl);
    }
}
