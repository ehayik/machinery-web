package com.github.eljaiek.machinery.web.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(properties = "machinery.web.jackson.performance-mode=false")
class JacksonPerformanceModeDisabledConfigTest {

    @Autowired ObjectMapper objectMapper;

    @Test
    @EnabledOnJre(JAVA_8)
    void afterBurnerModuleShouldBeRegisteredWhenJavaVersionIsEqualTo8() {
        assertThat(objectMapper.getRegisteredModuleIds())
                .isNotEmpty()
                .doesNotContain("com.fasterxml.jackson.module.afterburner.AfterburnerModule");
    }
}
