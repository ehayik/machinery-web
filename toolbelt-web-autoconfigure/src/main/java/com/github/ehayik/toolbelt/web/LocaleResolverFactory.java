package com.github.ehayik.toolbelt.web;

import static com.github.ehayik.toolbelt.web.WebProperties.Iln8Properties.LocaleResolverStrategy.COOKIE;

import com.github.ehayik.toolbelt.web.WebProperties.Iln8Properties.LocaleResolverStrategy;
import java.util.Locale;
import lombok.NonNull;
import lombok.val;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

class LocaleResolverFactory {

    LocaleResolver create(@NonNull LocaleResolverStrategy strategy, Locale locale) {
        return COOKIE == strategy ? newCookieLocaleResolver(locale) : newSessionLocaleResolver(locale);
    }

    private static CookieLocaleResolver newCookieLocaleResolver(Locale locale) {
        val slr = new CookieLocaleResolver();
        slr.setCookieName("toolbelt.web.i8ln.locale");
        slr.setDefaultLocale(locale);
        return slr;
    }

    private static SessionLocaleResolver newSessionLocaleResolver(Locale locale) {
        val slr = new SessionLocaleResolver();
        slr.setDefaultLocale(locale);
        return slr;
    }
}
