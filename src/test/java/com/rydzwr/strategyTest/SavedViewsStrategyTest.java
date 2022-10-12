package com.rydzwr.strategyTest;

import com.rydzwr.dto.NameJson;
import com.rydzwr.strategy.SavedViewsStrategy;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SavedViewsStrategyTest {

    @Test
    void shouldReturnHalViewCase() {
        // GIVEN
        SavedViewsStrategy strategy = new SavedViewsStrategy();
        NameJson given = new NameJson("hal", null);
        NameJson valid = new NameJson("halView", "My mind is going. I can feel it");

        // WHEN
        NameJson toTest = strategy.buildResponse(given);

        // THEN
        assertThat(valid.getValue(), equalTo(toTest.getValue()));
        assertThat(valid.getContent(), equalTo(toTest.getContent()));
    }

    @Test
    void shouldReturnDavidViewCase() {
        // GIVEN
        SavedViewsStrategy strategy = new SavedViewsStrategy();
        NameJson given = new NameJson("david", null);
        NameJson valid = new NameJson("davidView", "David here");

        // WHEN
        NameJson toTest = strategy.buildResponse(given);

        // THEN
        assertThat(valid.getValue(), equalTo(toTest.getValue()));
        assertThat(valid.getContent(), equalTo(toTest.getContent()));
    }
}
