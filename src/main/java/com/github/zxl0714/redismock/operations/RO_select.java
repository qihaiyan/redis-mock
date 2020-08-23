package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Response;
import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.OperationExecutorState;

import java.util.List;

public class RO_select implements RedisOperation {
    private OperationExecutorState state;
    private List<Slice> params;

    public RO_select(OperationExecutorState state, List<Slice> params){
        this.params = params;
        this.state = state;
    }

    @Override
    public Slice execute() {
        int selectedRedisBase = Integer.parseInt(new String(params.get(0).data()));
        state.changeActiveRedisBase(selectedRedisBase);
        return Response.OK;
    }
}
