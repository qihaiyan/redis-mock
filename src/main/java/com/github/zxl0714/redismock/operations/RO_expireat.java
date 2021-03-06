package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

import static com.github.zxl0714.redismock.Utils.convertToLong;

class RO_expireat extends AbstractRedisOperation {
    RO_expireat(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        long deadline = convertToLong(new String(params().get(1).data())) * 1000;
        return Response.integer(base().setDeadline(params().get(0), deadline));
    }
}
