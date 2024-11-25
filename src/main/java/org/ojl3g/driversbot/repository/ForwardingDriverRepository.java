package org.ojl3g.driversbot.repository;

import org.ojl3g.driversbot.model.ForwardingDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForwardingDriverRepository extends JpaRepository<ForwardingDriver, Long> {
    boolean existsByChatId(long chatId);

    Optional<ForwardingDriver> findByChatId(long chatId);


}
