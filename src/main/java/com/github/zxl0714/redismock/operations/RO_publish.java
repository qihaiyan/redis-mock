package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.RedisClient;
import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;
import java.util.Set;

class RO_publish extends AbstractRedisOperation {

    RO_publish(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        Slice channel = params().get(0);
        Slice message = params().get(1);

        Set<RedisClient> subscibers = base().getSubscribers(channel);

        subscibers.forEach(subscriber -> {
            Slice response = Response.publishedMessage(channel, message);
            subscriber.sendResponse(response, "contacting subscriber");
        });

        return Response.integer(subscibers.size());
    }
}
