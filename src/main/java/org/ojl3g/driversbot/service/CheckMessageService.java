package org.ojl3g.driversbot.service;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
@Service
public class CheckMessageService {

    public boolean hasMessageAndHasText(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
