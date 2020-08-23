package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.OperationExecutorState;

public class RO_quit implements RedisOperation {
    private OperationExecutorState state;

    public RO_quit(OperationExecutorState state) {
        this.state = state;
    }

    @Override
    public Slice execute() {
        state.owner().sendResponse(Response.clientResponse("quit", Response.OK), "quit");
        state.owner().close();
        return Response.SKIP;
    }
}
