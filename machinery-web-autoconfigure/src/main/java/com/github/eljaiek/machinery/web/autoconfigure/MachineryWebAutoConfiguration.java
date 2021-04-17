package com.github.eljaiek.machinery.web.autoconfigure;

import java.util.Locale;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@ConfigurationPropertiesScan
@ConditionalOnWebApplication
@Import(JacksonAddonsConfiguration.class)
@SuppressWarnings({"unused", "java:S1118"})
class MachineryWebAutoConfiguration {

    @Configuration
    @ComponentScan("com.gtihub.eljaiek.machinery.web.i8ln")
    @ConditionalOnClass(name = "com.gtihub.eljaiek.machinery.web.i8ln.I8lnModuleConfiguration")
    static class I8lnAutoConfiguration implements WebMvcConfigurer {

        @Value("${machinery.web.iln8.locale.set-default:us}")
        String defaultLocale;

        @Value("${machinery.web.iln8.locale.param-name:lang}")
        String paramName;

        @Bean
        @ConditionalOnMissingBean
        public LocaleResolver localeResolver() {
            val slr = new CookieLocaleResolver();
            slr.setCookieName("machinery.web.i8ln.locale");
            slr.setDefaultLocale(new Locale(defaultLocale));
            return slr;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(localeChangeInterceptor());
        }

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
            val lci = new LocaleChangeInterceptor();
            lci.setParamName(paramName);
            return lci;
        }
    }
}
