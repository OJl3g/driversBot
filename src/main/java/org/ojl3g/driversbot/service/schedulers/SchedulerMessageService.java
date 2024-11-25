package org.ojl3g.driversbot.service.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ojl3g.driversbot.bot.Bot;
import org.ojl3g.driversbot.model.ForwardingDriver;
import org.ojl3g.driversbot.service.ForwardingDriverService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerMessageService {

    private final ForwardingDriverService forwardingDriverService;
    private final Bot bot;

    @Scheduled(cron = "0 0 4 * * MON-FRI", zone = "Europe/Moscow")
    @Scheduled(cron = "0 31 14 * * MON-FRI", zone = "Europe/Moscow")
    public void sendMessageAllDrivers() {

      log.info("sendMessageAllDrivers start");
        List<ForwardingDriver> allForwardingDrivers =
                forwardingDriverService.getAllForwardingDrivers();

        for (ForwardingDriver driver : allForwardingDrivers) {
            bot.sendMessage(driver.getChatId(),"А ты сейчас на каком адресе?");
        }

    }
}
