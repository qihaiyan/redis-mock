package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

class RO_llen extends AbstractRedisOperation {
    RO_llen(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        LinkedList<Slice> list = getDataFromBase(key, Lists.newLinkedList());
        return Response.integer(list.size());
    }
}
