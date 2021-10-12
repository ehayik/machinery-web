package com.github.ehayik.toolbelt.web.i8ln;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@RequiredArgsConstructor
class ReloadableMessageSourceBundle implements MessageBundle {

    private final ReloadableResourceBundleMessageSource messageSource;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[] {}, getLocale());
    }

    @Override
    public String getMessageOrDefault(String code, String defaultMessage) {
        return messageSource.getMessage(code, new Object[] {}, defaultMessage, getLocale());
    }

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, getLocale());
    }

    @Override
    public void register(String... basenames) {
        messageSource.addBasenames(basenames);
    }
}
