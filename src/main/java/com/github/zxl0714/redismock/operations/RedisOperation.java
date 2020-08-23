package com.github.zxl0714.redismock.operations;

import com.github.zxl0714.redismock.storage.RedisBase;
import com.github.zxl0714.redismock.server.Slice;

/**
 * Represents a Redis Operation which can be executed against {@link RedisBase}
 */
public interface RedisOperation {
    Slice execute();
}
