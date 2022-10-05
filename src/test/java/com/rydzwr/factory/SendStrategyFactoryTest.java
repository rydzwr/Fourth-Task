package com.rydzwr.factory;

import com.rydzwr.service.SendStrategyFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SendStrategyFactoryTest {
    @Test
    public void createConstructor() throws IOException, ClassNotFoundException {
        SendStrategyFactory strategyFactory = new SendStrategyFactory();
    }
}
