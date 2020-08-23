package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

import static com.github.zxl0714.redismock.Utils.deserializeObject;

class RO_pfcount extends AbstractRedisOperation {
    RO_pfcount(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Set<Slice> set = Sets.newHashSet();
        for (Slice key : params()) {
            Slice data = base().getValue(key);
            if (data == null) {
                continue;
            }

            Set<Slice> s = deserializeObject(data);
            set.addAll(s);
        }
        return Response.integer((long) set.size());
    }
}
