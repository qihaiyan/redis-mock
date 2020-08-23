package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.OperationExecutorState;

public class RO_multi implements RedisOperation {
    private OperationExecutorState state;

    RO_multi(OperationExecutorState state){
        this.state = state;
    }

    @Override
    public Slice execute() {
        state.newTransaction();
        return Response.OK;
    }
}
