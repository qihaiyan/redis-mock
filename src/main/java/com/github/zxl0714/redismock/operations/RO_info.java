package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;

class RO_info implements RedisOperation {
    @Override
    public Slice execute() {
        return Response.bulkString(Slice.create("Redis Mock Server Info"));
    }
}
