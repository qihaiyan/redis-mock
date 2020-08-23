package com.github.zxl0714.redismock.commands;

import com.github.zxl0714.redismock.exception.ParseErrorException;
import com.github.zxl0714.redismock.server.SliceParser;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Xiaolu on 2015/4/20.
 */
public class RedisCommandParser {

    @VisibleForTesting
    public static RedisCommand parse(String stringInput) throws ParseErrorException {
        Preconditions.checkNotNull(stringInput);

        return parse(new ByteArrayInputStream(stringInput.getBytes()));
    }

    public static RedisCommand parse(InputStream messageInput) throws ParseErrorException {
        Preconditions.checkNotNull(messageInput);

        long count = SliceParser.consumeCount(messageInput);
        if (count == 0) {
            throw new ParseErrorException();
        }
        RedisCommand command = RedisCommand.create();
        for (long i = 0; i < count; i++) {
            command.parameters().add(SliceParser.consumeParameter(messageInput));
        }
        return command;
    }
}
