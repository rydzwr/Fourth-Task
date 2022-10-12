package com.rydzwr.service;

import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.strategy.SavedViewsStrategy;
import com.rydzwr.strategy.SendEasterEggStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendStrategyServiceTest {

    private final Map<String, Class<? extends SendMethodStrategy>> validMap = new HashMap<>();

    @BeforeEach
    public void init() {
        buildMap();
    }

    @Test
    public void mapsShouldBeEqual() throws Exception {
        SendStrategyService service = new SendStrategyService();
        Map<String, SendMethodStrategy> toTest = service.getMap();
        Map<String, Class<? extends SendMethodStrategy>> classes = new HashMap<>();

        for (Map.Entry<String, SendMethodStrategy> pair: toTest.entrySet()) {
            classes.put(pair.getKey(), pair.getValue().getClass());
        }

        assertEquals(validMap, classes);
    }

    private void buildMap() {
        validMap.put("hal", SavedViewsStrategy.class);
        validMap.put("david", SavedViewsStrategy.class);
        validMap.put("Johny", SendEasterEggStrategy.class);
    }
}
