package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.ArrayList;
import java.util.List;

public class RO_hmget extends AbstractRedisOperation {
    public RO_hmget(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        ArrayList<Slice> result = new ArrayList<>();
        Slice hash = params().get(0);

        for(int i = 1; i < params().size(); i ++){
            Slice field = params().get(i);
            Slice value = base().getValue(hash, field);

            if(value == null){
                result.add(Response.NULL);
            } else {
                result.add(Response.bulkString(value));
            }
        }

        return Response.array(result);
    }
}
