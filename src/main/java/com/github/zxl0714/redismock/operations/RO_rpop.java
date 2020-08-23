package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;

import java.util.LinkedList;
import java.util.List;

class RO_rpop extends RO_pop<LinkedList<Slice>> {
    RO_rpop(RedisBase base, List<Slice> params ) {
        super(base, params);
    }

    @Override
    Slice popper(LinkedList<Slice> list) {
        return list.removeLast();
    }
}
