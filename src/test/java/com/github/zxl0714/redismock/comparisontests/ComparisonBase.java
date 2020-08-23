package com.github.zxl0714.redismock.comparisontests;

import com.github.zxl0714.redismock.RedisServer;
import com.github.zxl0714.redismock.util.EmbeddedRedis;
import org.junit.jupiter.api.extension.*;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ComparisonBase implements TestTemplateInvocationContextProvider,
        BeforeAllCallback, AfterAllCallback {
    private static RedisServer fakeServer;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        //Start up the real redis server
        EmbeddedRedis.start();

        //Start up the fake redis server
        fakeServer = RedisServer.newRedisServer(EmbeddedRedis.PORT + 1);
        fakeServer.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        //Kill the real redis server
        EmbeddedRedis.stop();

        //Kill the fake redis server
        fakeServer.stop();
    }


    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Stream.of(
                new JedisTestTemplateInvocationContext("mock",
                        new Jedis(fakeServer.getHost(), fakeServer.getBindPort(), 1000000)),
                new JedisTestTemplateInvocationContext("real",
                        new Jedis("localhost", EmbeddedRedis.PORT)));
    }

    private class JedisTestTemplateInvocationContext implements TestTemplateInvocationContext{

        private final String displayName;
        private final Jedis jedis;

        private JedisTestTemplateInvocationContext(String displayName, Jedis jedis) {
            this.displayName = displayName;
            this.jedis = jedis;
        }

        @Override
        public String getDisplayName(int invocationIndex) {
            return displayName;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return Collections.singletonList(new ParameterResolver() {
                @Override
                public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                    return parameterContext.getParameter().getType() == Jedis.class;
                }

                @Override
                public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                    return jedis;
                }
            });
        }
    }

}
