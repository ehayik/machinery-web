package com.gtihub.eljaiek.machinery.web.i8ln;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Slf4j
@RequiredArgsConstructor
class MessageSourceBundle implements MessageBundle {

    private final MessageSource messageSource;

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

        if (messageSource instanceof ReloadableResourceBundleMessageSource) {
            ((ReloadableResourceBundleMessageSource) messageSource).addBasenames(basenames);
            return;
        }

        log.debug(
                "Could not register resource bundles {}. MessageSource Bean isn't instance of ReloadableResourceBundleMessageSource",
                (Object) basenames);
    }
}
