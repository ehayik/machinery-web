package com.github.ehayik.toolbelt.web;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.github.ehayik.toolbelt.web.WebProperties.Iln8Properties.LocaleResolverStrategy;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@ComponentScan("com.github.ehayik.toolbelt.web.i8ln")
@ConditionalOnClass(name = "com.github.ehayik.toolbelt.web.i8ln.I8lnModuleConfiguration")
class I8lnAutoConfiguration implements WebMvcConfigurer {

    @Value("${toolbelt.web.iln8.locale.param-name:lang}")
    private String paramName;

    @Value("${toolbelt.web.iln8.locale.resolver-strategy:COOKIE}")
    private LocaleResolverStrategy resolverStrategy;

    @Value("${toolbelt.web.iln8.locale.set-default:}")
    private String defaultLocale;

    @Bean
    @ConditionalOnMissingBean
    @SuppressWarnings("unused")
    LocaleResolver localeResolver() {
        return new LocaleResolverFactory().create(resolverStrategy, toLocale(defaultLocale));
    }

    private static Locale toLocale(String value) {

        if (isEmpty(value)) {
            return Locale.getDefault();
        }

        String[] langCountry = value.split("_");
        return new Locale(langCountry[0], langCountry.length > 1 ? langCountry[1] : "");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName(paramName);
        return lci;
    }
}
