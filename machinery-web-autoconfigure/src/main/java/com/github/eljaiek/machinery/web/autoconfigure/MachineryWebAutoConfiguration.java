package com.github.eljaiek.machinery.web.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConfigurationPropertiesScan
@ConditionalOnWebApplication
@Import(JacksonAddonsConfiguration.class)
class MachineryWebAutoConfiguration {}
