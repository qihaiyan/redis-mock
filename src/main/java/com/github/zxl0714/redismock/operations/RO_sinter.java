package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.zxl0714.redismock.Utils.deserializeObject;

class RO_sinter extends AbstractRedisOperation {
    RO_sinter(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Set<Slice> resultSoFar = getSet(key);

        for(int i = 1; i < params().size(); i++){
            Set<Slice> set = getSet(params().get(i));
            resultSoFar = Sets.intersection(resultSoFar, set);
        }

        ImmutableList.Builder<Slice> builder = new ImmutableList.Builder<Slice>();
        resultSoFar.forEach(element -> builder.add(Response.bulkString(element)));

        return Response.array(builder.build());
    }

    private Set<Slice> getSet(Slice key){
        Set<Slice> set;
        Slice data = base().getValue(key);
        if (data != null) {
            set = new HashSet<>(deserializeObject(data));
        } else {
            set = new HashSet<>();
        }
        return set;
    }
}
