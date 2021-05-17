package com.github.eljaiek.machinery.web.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gtihub.eljaiek.machinery.web.i8ln.MessageBundle;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@TestInstance(PER_CLASS)
@WebMvcTest(MessageController.class)
class I8lnAutoConfigurationTests {

    static final String LOCALE_COOKIE = "machinery.web.i8ln.locale";

    @Autowired MockMvc mockMvc;
    @Autowired MessageBundle messageBundle;

    @BeforeAll
    void setup() {
        messageBundle.register("classpath:messages-test");
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void defaultConfigShouldWork() {
        // When
        val message =
                mockMvc.perform(get("/greeting"))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        // Then
        assertThat(message).isEqualTo("Hey, It works");
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void defaultLocaleChangeInterceptorConfigShouldWork() {
        // When
        val message =
                mockMvc.perform(get("/greeting").param("lang", "es_CU"))
                        .andExpect(status().isOk())
                        .andExpect(cookie().exists(LOCALE_COOKIE))
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        // Then
        assertThat(message).isEqualTo("En hora buena, funciona !!!");
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void registerAdditionalResourceBundlesShouldWork() {
        // When
        val message =
                mockMvc.perform(get("/greeting/personalized").param("name", "Lucas"))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        // Then
        assertThat(message).isEqualTo("Hi Lucas, How are you doing ?");
    }
}
