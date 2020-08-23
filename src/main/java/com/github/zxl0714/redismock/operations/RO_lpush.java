package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

class RO_lpush extends RO_add<LinkedList<Slice>> {
    RO_lpush(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    void addSliceToCollection(LinkedList<Slice> list, Slice slice) {
        list.addFirst(slice);
    }

    @Override
    LinkedList<Slice> getDefaultResponse() {
        return Lists.newLinkedList();
    }

}
