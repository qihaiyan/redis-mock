package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

class RO_spop extends RO_pop<Set<Slice>> {
    RO_spop(RedisBase base, List<Slice> params ) {
        super(base, params);
    }

    @Override
    Slice popper(Set<Slice> collection) {
        Iterator<Slice> it = collection.iterator();
        Slice v = it.next();
        it.remove();
        return v;
    }
}
