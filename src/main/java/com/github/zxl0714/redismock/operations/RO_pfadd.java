package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

import static com.github.zxl0714.redismock.Utils.deserializeObject;
import static com.github.zxl0714.redismock.Utils.serializeObject;

class RO_pfadd extends AbstractRedisOperation {
    RO_pfadd(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        Slice key = params().get(0);
        Slice data = base().getValue(key);
        boolean first;

        Set<Slice> set;
        int prev;
        if (data == null) {
            set = Sets.newHashSet();
            first = true;
            prev = 0;
        } else {
            set = deserializeObject(data);
            first = false;
            prev = set.size();
        }

        for (Slice v : params().subList(1, params().size())) {
            set.add(v);
        }

        Slice out = serializeObject(set);
        if (first) {
            base().putValue(key, out);
        } else {
            base().putValue(key, out, null);
        }

        if (prev != set.size()) {
            return Response.integer(1L);
        }
        return Response.integer(0L);
    }
}
