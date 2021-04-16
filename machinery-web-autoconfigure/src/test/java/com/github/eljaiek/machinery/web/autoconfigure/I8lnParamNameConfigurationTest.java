package com.github.eljaiek.machinery.web.autoconfigure;

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
        properties = "machinery.web.iln8.locale.param-name=locale")
class I8lnParamNameConfigurationTest {

    @Autowired MockMvc mockMvc;

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void defaultLocaleChangeInterceptorConfigShouldWork() {
        // When
        val message =
                mockMvc.perform(get("/greeting").param("locale", "es"))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        // Then
        assertThat(message).isEqualTo("En hora buena, funciona !!!");
    }
}
