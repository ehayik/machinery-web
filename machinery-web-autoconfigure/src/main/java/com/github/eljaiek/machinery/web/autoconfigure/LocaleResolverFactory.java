package com.github.eljaiek.machinery.web.autoconfigure;

import static com.github.eljaiek.machinery.web.autoconfigure.MachineryWebProperties.Iln8Properties.LocaleResolverStrategy.COOKIE;

import com.github.eljaiek.machinery.web.autoconfigure.MachineryWebProperties.Iln8Properties.LocaleResolverStrategy;
import java.util.Locale;
import lombok.NonNull;
import lombok.val;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

class LocaleResolverFactory {

    LocaleResolver create(@NonNull LocaleResolverStrategy strategy, Locale locale) {

        if (COOKIE == strategy) {
            return cookieLocaleResolver(locale);
        }

        return sessionLocaleResolver(locale);
    }

    private static CookieLocaleResolver cookieLocaleResolver(Locale locale) {
        val slr = new CookieLocaleResolver();
        slr.setCookieName("machinery.web.i8ln.locale");
        slr.setDefaultLocale(locale);
        return slr;
    }

    private static SessionLocaleResolver sessionLocaleResolver(Locale locale) {
        val slr = new SessionLocaleResolver();
        slr.setDefaultLocale(locale);
        return slr;
    }
}
