package com.github.ehayik.toolbelt.web;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "toolbelt.web")
class WebProperties {

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

        LocaleProperties locale;

        @Value
        static class LocaleProperties {
            /**
             * Set the name of the parameter that contains a locale specification in a locale change
             * request. Default is "lang"
             */
            String paramName;

            /** Set a default Locale that will be return if no other locale found. */
            String setDefault;

            /**
             * Set locale resolution strategy that allows for both locale resolution via the request
             * and locale modification via request and response. Default is "COOKIE"
             */
            LocaleResolverStrategy resolverStrategy;
        }

        enum LocaleResolverStrategy {
            COOKIE,
            SESSION
        }
    }
}
