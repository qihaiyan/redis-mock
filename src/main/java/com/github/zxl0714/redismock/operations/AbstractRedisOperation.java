package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.server.Slice;
import com.github.zxl0714.redismock.storage.RedisBase;

import java.util.List;

import static com.github.zxl0714.redismock.Utils.deserializeObject;

abstract class AbstractRedisOperation implements RedisOperation {
    private final RedisBase base;
    private final List<Slice> params;

    AbstractRedisOperation(RedisBase base, List<Slice> params) {
        this.base = base;
        this.params = params;
    }

    void doOptionalWork(){
        //Place Holder For Ops which need to so some operational work
    }

    abstract Slice response();

    RedisBase base(){
        return base;
    }

    List<Slice> params(){
        return params;
    }

    <V> V getDataFromBase(Slice key, V defaultResponse){
        Slice data = base().getValue(key);
        if (data != null) {
            return deserializeObject(data);
        } else {
            return defaultResponse;
        }
    }

    @Override
    public Slice execute(){
        try {
            doOptionalWork();
            synchronized (base) {
                return response();
            }
        } catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Invalid number of arguments when executing command [" + getClass().getSimpleName() + "]", e);
        }
    }
}
