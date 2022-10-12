package com.rydzwr.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class NameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultFoo() throws Exception {
        String fooJson = """
                {
                    "value": "Foo",
                    "content": ""
                }
                """;

        String expected = """
                {"value":"Foo","content":""}""";
        this.mockMvc.perform(
                        post("/api/v1/validator")
                                .contentType(APPLICATION_JSON)
                                .content(fooJson))
                .andDo(print()).andExpect(content().string(expected.trim()));
    }
    @Test
    public void shouldReturnHalView() throws Exception {
        String halJson = """
                {
                    "value": "hal",
                    "content": ""
                }
                """;

        String expected = """
                {"value":"halView","content":"My mind is going. I can feel it"}""";
        this.mockMvc.perform(
                        post("/api/v1/validator")
                                .contentType(APPLICATION_JSON)
                                .content(halJson))
                .andDo(print()).andExpect(content().string(expected.trim()));
    }

    @Test
    public void shouldReturnDavidView() throws Exception {
        String halJson = """
                {
                    "value": "david",
                    "content": ""
                }
                """;

        String expected = """
                {"value":"davidView","content":"David here"}""";
        this.mockMvc.perform(
                        post("/api/v1/validator")
                                .contentType(APPLICATION_JSON)
                                .content(halJson))
                .andDo(print()).andExpect(content().string(expected.trim()));
    }

    @Test
    public void shouldThrowError() throws Exception {
        String emptyJson = """
                {
                    "": "",
                    "": ""
                }
                """;
        this.mockMvc.perform(
                        post("/api/v1/validator")
                                .contentType(APPLICATION_JSON)
                                .content(emptyJson))
                .andDo(print()).andExpect((status().is4xxClientError()));
    }

    @Test
    public void shouldReturnIAmTeapot() throws Exception {
        String johnyJson = """
                {
                    "value": "Johny",
                    "content": ""
                }
                """;
        this.mockMvc.perform(
                        post("/api/v1/validator")
                                .contentType(APPLICATION_JSON)
                                .content(johnyJson))
                .andDo(print()).andExpect((status().isIAmATeapot()));
    }
}