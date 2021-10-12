package com.github.ehayik.toolbelt.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
        controllers = MessageController.class,
        properties = "toolbelt.web.iln8.locale.set-default=es_CU")
class I8lnDefaultLocaleConfigurationTest {

    @Autowired MockMvc mockMvc;

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void defaultLocaleShouldBeOverwritten() {
        // When
        val message =
                mockMvc.perform(get("/greeting"))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        // Then
        assertThat(message).isEqualTo("En hora buena, funciona !!!");
    }
}
