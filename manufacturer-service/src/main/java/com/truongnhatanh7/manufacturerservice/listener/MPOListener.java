package com.truongnhatanh7.manufacturerservice.listener;

import com.truongnhatanh7.manufacturerservice.event.RollbackProductEvent;
import com.truongnhatanh7.manufacturerservice.service.MPOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(groupId = "groupMpo", topics = "rollback.product")
@Slf4j
public class MPOListener {
    private MPOService mpoService;

    public MPOListener(MPOService mpoService) {
        this.mpoService = mpoService;
    }

    @KafkaHandler
    public void handleRollbackProduct(RollbackProductEvent rollbackProductEvent) {
        mpoService.handleMpoRollback(rollbackProductEvent.getMpoId());
    }
}
