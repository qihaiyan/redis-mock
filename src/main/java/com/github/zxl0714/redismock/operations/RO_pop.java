package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.Utils;

import java.util.Collection;
import java.util.List;

abstract class RO_pop<V extends Collection<Slice>> extends AbstractRedisOperation {
    RO_pop(RedisBase base, List<Slice> params ) {
        super(base, params);
    }

    abstract Slice popper(V list);

    Slice response() {
        Slice key = params().get(0);
        V list = getDataFromBase(key, null);
        if(list == null || list.isEmpty()) return Response.NULL;
        Slice v = popper(list);
        base().putValue(key, Utils.serializeObject(list));
        return Response.bulkString(v);
    }
}
