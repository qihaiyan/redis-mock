package com.github.zxl0714.redismock.storage;

import com.github.zxl0714.redismock.operations.RedisOperation;
import com.github.zxl0714.redismock.server.RedisClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class OperationExecutorState {
    private final RedisClient owner;
    private final Map<Integer, RedisBase> redisBases;
    private AtomicBoolean isTransactionModeOn = new AtomicBoolean(false);
    private List<RedisOperation> tx = new ArrayList<>();
    private int selectedRedisBase = 0;

    public OperationExecutorState(RedisClient owner, Map<Integer, RedisBase> redisBases){
        this.owner = owner;
        this.redisBases = redisBases;
    }

    public RedisBase base(){
        return redisBases.computeIfAbsent(selectedRedisBase, key -> new RedisBase());
    }

    public RedisClient owner(){
        return owner;
    }

    public List<RedisOperation> tx(){
        return tx;
    }

    public void changeActiveRedisBase(int selectedRedisBase) {
        this.selectedRedisBase = selectedRedisBase;
    }

    public void transactionMode(boolean isTransactionModeOn){
        this.isTransactionModeOn.set(isTransactionModeOn);
    }

    public boolean isTransactionModeOn(){
        return isTransactionModeOn.get();
    }

    public void newTransaction(){
        if(isTransactionModeOn.get()) throw new RuntimeException("Redis mock does not support more than one transaction");
        transactionMode(true);
    }
}
