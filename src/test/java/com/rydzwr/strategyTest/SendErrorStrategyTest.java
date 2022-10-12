package com.rydzwr.strategyTest;

import com.rydzwr.dto.NameJson;
import com.rydzwr.exception.MissingParameterException;
import com.rydzwr.strategy.SendErrorStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class SendErrorStrategyTest {

    @Test
    void shouldSendError() {
        //GIVEN
        SendErrorStrategy strategy = new SendErrorStrategy();
        NameJson given = new NameJson(null, null);

        //WHEN + THEN
        var exception = catchThrowable(() ->  strategy.buildResponse(given));
        assertThat(exception)
                .isInstanceOf(MissingParameterException.class)
                .hasMessageContaining("Missing name parameter");
    }
}
