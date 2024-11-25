package org.ojl3g.driversbot.bot;

import org.ojl3g.driversbot.model.ForwardingDriver;
import org.ojl3g.driversbot.service.CheckMessageService;
import org.ojl3g.driversbot.service.ForwardingDriverService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component //что бы spring управлял bean
public class Bot extends TelegramLongPollingBot {
    private final CheckMessageService checkMessageService;
    private final ForwardingDriverService forwardingDriverService;

    @Value("${bot.username}")
    private String userName;

    public Bot(@Value("${bot.token}") String botToken,
               CheckMessageService checkMessageService,
               ForwardingDriverService forwardingDriverService) {

        super(botToken);
        this.checkMessageService = checkMessageService;
        this.forwardingDriverService = forwardingDriverService;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (checkMessageService.hasMessageAndHasText(update) == false) return;

        Long chatId = update.getMessage().getChatId();

        ForwardingDriver currentForwardDriver;
        // user is not database
        if (forwardingDriverService.existForwardingDriver(chatId) == false) {

            //Add forwardDriver in temporary list
            if (forwardingDriverService.findTemporaryForwardingDriver(chatId) == false) {
                forwardingDriverService.addForwardingDriverList(chatId);
                String responseText = "Введите ФИО для регистрации";
                sendMessage(chatId, responseText);
                return;

            } else {
                //Registration
                String fullName = update.getMessage().getText();
                ForwardingDriver forwardingDriver = new ForwardingDriver();
                forwardingDriver.setChatId(chatId);
                forwardingDriver.setFullName(fullName);
                forwardingDriverService.saveForwardingDriver(forwardingDriver);
                sendMessage(chatId, "Вы Зарегистрировались");
                return;
            }
        }

        currentForwardDriver = forwardingDriverService.findForwardingDriver(chatId);




    }

    public void sendMessage(Long chatId ,String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
