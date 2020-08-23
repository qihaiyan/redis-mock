package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

import static com.github.zxl0714.redismock.Utils.convertToLong;

class RO_pexpire extends AbstractRedisOperation {
    RO_pexpire(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    long getValue(List<Slice> params){
        return convertToLong(new String(params.get(1).data()));
    }

    Slice response() {
        return Response.integer(base().setTTL(params().get(0), getValue(params())));
    }
}
