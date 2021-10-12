package com.github.ehayik.toolbelt.web;

import com.github.ehayik.toolbelt.web.i8ln.MessageBundle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MessageController {

    private final MessageBundle messageBundle;

    @GetMapping("/greeting")
    String greets() {
        return messageBundle.getMessage("app.greeting");
    }

    @GetMapping("/greeting/personalized")
    String personalizedGreets(@RequestParam String name) {
        return messageBundle.getMessage("app.greeting.personalized", name);
    }
}
