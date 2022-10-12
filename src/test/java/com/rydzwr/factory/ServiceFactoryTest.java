package com.rydzwr.factory;

import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.strategy.CommonNameStrategy;
import com.rydzwr.strategy.SavedViewsStrategy;
import com.rydzwr.strategy.SendEasterEggStrategy;
import com.rydzwr.strategy.SendErrorStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceFactoryTest {
    ServiceFactory factory = new ServiceFactory();

    @Test
    void shouldReturnCommonNameStrategy(){
        //GIVEN
        SendMethodStrategy toTest = factory.getStrategy("foo");

        //WHEN + THEN
        assertEquals(toTest.getClass(), CommonNameStrategy.class);
    }

    @Test
    void shouldReturnSavedNameStrategyForHalName(){
        //GIVEN
        SendMethodStrategy toTest = factory.getStrategy("hal");

        //WHEN + THEN
        assertEquals(toTest.getClass(), SavedViewsStrategy.class);
    }

    @Test
    void shouldReturnSavedNameStrategyForDavidName(){
        //GIVEN
        SendMethodStrategy toTest = factory.getStrategy("david");

        //WHEN + THEN
        assertEquals(toTest.getClass(), SavedViewsStrategy.class);
    }

    @Test
    void shouldReturnSendEasterEggErrorStrategy(){
        //GIVEN
        SendMethodStrategy toTest = factory.getStrategy("Johny");

        //WHEN + THEN
        assertEquals(toTest.getClass(), SendEasterEggStrategy.class);
    }

    @Test
    void shouldSendErrorStrategy() {
        //GIVEN
        SendMethodStrategy toTest = factory.getStrategy(null);

        //WHEN + THEN
        assertEquals(SendErrorStrategy.class, toTest.getClass());
    }
}
