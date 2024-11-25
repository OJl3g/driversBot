package org.ojl3g.driversbot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.ojl3g.driversbot.model.ForwardingDriver;
import org.ojl3g.driversbot.repository.ForwardingDriverRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ForwardingDriverService {
    private final ForwardingDriverRepository forwardingDriverRepository;
    private List<Long> temporaryForRegistration = new ArrayList<>();

    public List<ForwardingDriver> getAllForwardingDrivers() {
        return forwardingDriverRepository.findAll();
    }



    public void addForwardingDriverList(Long chatId) {
        temporaryForRegistration.add(chatId);
    }

    public boolean findTemporaryForwardingDriver(Long forwardingDriverId) {
        return temporaryForRegistration.contains(forwardingDriverId);
    }


    public ForwardingDriver saveForwardingDriver(ForwardingDriver forwardingDriver) {
        ForwardingDriver save = forwardingDriverRepository.save(forwardingDriver);
        return save;
    }

    public boolean existForwardingDriver(Long chatId) {
        return forwardingDriverRepository.existsById(chatId);
    }

    public ForwardingDriver findForwardingDriver(Long chatId) {
        return forwardingDriverRepository.findByChatId(chatId)
                .orElse(null);

    }

}


