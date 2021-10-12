package com.github.ehayik.toolbelt.web.i8ln;

public interface MessageBundle {

    String getMessage(String code);

    String getMessageOrDefault(String code, String defaultMessage);

    String getMessage(String code, Object... args);

    void register(String... basenames);
}
