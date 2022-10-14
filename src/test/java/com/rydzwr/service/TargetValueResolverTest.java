package com.rydzwr.service;

import com.rydzwr.dto.NameJson;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TargetValueResolverTest {

    @Test
    public void instancesShouldBeEqual() {
        // GIVEN
        TargetValueResolver resolverInstanceOne = TargetValueResolver.getInstance();
        TargetValueResolver resolverInstanceTwo = TargetValueResolver.getInstance();

        // WHEN + THEN
        assertEquals(resolverInstanceOne, resolverInstanceTwo);
    }

    @Test
    public void shouldReturnHalValue() {
        // GIVEN
        NameJson given = new NameJson("hal", null);
        NameJson valid = new NameJson("halView", "My mind is going. I can feel it");

        NameJson toTest = TargetValueResolver.getInstance().resolve(given);

        // WHEN + THEN
        assertThat(valid.getValue(), equalTo(toTest.getValue()));
        assertThat(valid.getContent(), equalTo(toTest.getContent()));
    }

    @Test
    public void shouldReturnDavidValue() {
        // GIVEN
        NameJson given = new NameJson("david", null);
        NameJson valid = new NameJson("davidView", "David here");

        NameJson toTest = TargetValueResolver.getInstance().resolve(given);

        // WHEN + THEN
        assertThat(valid.getValue(), equalTo(toTest.getValue()));
        assertThat(valid.getContent(), equalTo(toTest.getContent()));
    }
}
