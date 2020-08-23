package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.OperationExecutorState;

import java.util.List;

public class RO_subscribe extends AbstractRedisOperation {
    private OperationExecutorState state;

    public RO_subscribe(OperationExecutorState state, List<Slice> params) {
        super(state.base(), params);
        this.state = state;
    }

    Slice response() {
        params().forEach(channel -> base().addSubscriber(channel, state.owner()));
        List<Slice> numSubscriptions = base().getSubscriptions(state.owner());

        return Response.subscribedToChannel(numSubscriptions);
    }
}
