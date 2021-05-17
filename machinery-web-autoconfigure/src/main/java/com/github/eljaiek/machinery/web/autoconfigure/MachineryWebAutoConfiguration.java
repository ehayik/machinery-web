package com.github.eljaiek.machinery.web.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConfigurationPropertiesScan
@ConditionalOnWebApplication
@SuppressWarnings({"unused", "java:S1118"})
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@Import({JacksonAddonsConfiguration.class, I8lnAutoConfiguration.class})
class MachineryWebAutoConfiguration {}
