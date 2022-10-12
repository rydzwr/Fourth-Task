package com.rydzwr.strategyTest;

import com.rydzwr.dto.NameJson;
import com.rydzwr.exception.EasterEggException;
import com.rydzwr.strategy.SendEasterEggStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SendEasterEggStrategyTest {

    @Test
    void shouldSend418Error() {
        //GIVEN
        SendEasterEggStrategy strategy = new SendEasterEggStrategy();
        NameJson given = new NameJson("Johny", null);

        //WHEN + THEN
        var exception = catchThrowable(() ->  strategy.buildResponse(given));
        assertThat(exception)
                .isInstanceOf(EasterEggException.class)
                .hasMessageContaining("I am a Teapot. You tried to use a teapot to brew coffee.");
    }
}
