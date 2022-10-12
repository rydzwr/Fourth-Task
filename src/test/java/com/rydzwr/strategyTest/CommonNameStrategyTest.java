package com.rydzwr.strategyTest;

import com.rydzwr.dto.NameJson;
import com.rydzwr.strategy.CommonNameStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonNameStrategyTest {
    @Test
    void shouldReturnGivenJSON() {
        // GIVEN
        CommonNameStrategy strategy = new CommonNameStrategy();
        NameJson valid = new NameJson("Foo", null);

        // WHEN
        NameJson toTest = strategy.buildResponse(valid);

        // THEN
        assertEquals(valid, toTest);
    }
}
