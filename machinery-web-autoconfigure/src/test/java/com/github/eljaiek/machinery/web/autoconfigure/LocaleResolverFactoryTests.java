package com.github.eljaiek.machinery.web.autoconfigure;

import static com.github.eljaiek.machinery.web.autoconfigure.MachineryWebProperties.Iln8Properties.LocaleResolverStrategy.COOKIE;
import static com.github.eljaiek.machinery.web.autoconfigure.MachineryWebProperties.Iln8Properties.LocaleResolverStrategy.SESSION;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

class LocaleResolverFactoryTests {

    LocaleResolverFactory localeResolverFactory;

    @BeforeEach
    void setUp() {
        localeResolverFactory = new LocaleResolverFactory();
    }

    @Test
    void createShouldReturnCookieLocaleResolver() {
        // When
        LocaleResolver localeResolver = localeResolverFactory.create(COOKIE, Locale.getDefault());

        // Then
        assertThat(localeResolver)
                .isInstanceOf(CookieLocaleResolver.class)
                .hasFieldOrPropertyWithValue("defaultLocale", Locale.getDefault());
    }

    @Test
    void createShouldReturnSessionLocaleResolver() {
        // When
        LocaleResolver localeResolver = localeResolverFactory.create(SESSION, Locale.getDefault());

        // Then
        assertThat(localeResolver)
                .isInstanceOf(SessionLocaleResolver.class)
                .hasFieldOrPropertyWithValue("defaultLocale", Locale.getDefault());
    }
}
