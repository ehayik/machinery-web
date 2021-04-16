package com.github.eljaiek.machinery.web.autoconfigure;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "machinery.web")
class MachineryWebProperties {

    Iln8Properties i8ln;
    JacksonProperties jackson;

    @Value
    static class JacksonProperties {
        /**
         * Toggle On/Off dynamic bytecode generation for standard Jackson POJO serializers and
         * deserializers. If application is running on Java 8. Support for Java 11+ will be added
         * after Spring Boot upgrades Jackson to version Jackson 2.12+.
         */
        boolean performanceMode;
    }

    @Value
    static class Iln8Properties {
        String paramName;
        LocaleProperties locale;

        @Value
        static class LocaleProperties {
            String paramName;
            String setDefault;
        }
    }
}
