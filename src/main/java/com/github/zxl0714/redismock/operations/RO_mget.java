package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;
import com.google.common.collect.ImmutableList;

import java.util.List;

class RO_mget extends AbstractRedisOperation {
    RO_mget(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        ImmutableList.Builder<Slice> builder = new ImmutableList.Builder<Slice>();
        for (Slice key : params()) {
            builder.add(Response.bulkString(base().getValue(key)));

        }
        return Response.array(builder.build());
    }
}
