package org.ojl3g.driversbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component //что бы spring управлял bean
public class Bot extends TelegramLongPollingBot {


    @Value("${bot.username}")
    private String userName;


    public Bot(@Value("${bot.tocken}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());

    }

    @Override
    public String getBotUsername() {
        return userName;
    }
}
