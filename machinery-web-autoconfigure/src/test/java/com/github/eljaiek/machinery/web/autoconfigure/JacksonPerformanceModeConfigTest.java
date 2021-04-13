package com.github.eljaiek.machinery.web.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class JacksonPerformanceModeConfigTest {

    @Autowired ObjectMapper objectMapper;

    @Test
    @EnabledOnJre(JAVA_8)
    void afterBurnerModuleShouldBeRegisteredWhenJavaVersionIsEqualTo8() {
        assertThat(objectMapper.getRegisteredModuleIds())
                .contains("com.fasterxml.jackson.module.afterburner.AfterburnerModule");
    }

    @Test
    @DisabledOnJre(JAVA_8)
    void afterBurnerModuleShouldNotBeRegisteredWhenJavaVersionIsBiggerThan8() {
        assertThat(objectMapper.getRegisteredModuleIds())
                .doesNotContain("com.fasterxml.jackson.module.afterburner.AfterburnerModule");
    }
}
