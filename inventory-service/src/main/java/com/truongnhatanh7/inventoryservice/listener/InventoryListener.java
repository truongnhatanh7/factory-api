package com.truongnhatanh7.inventoryservice.listener;

import com.truongnhatanh7.inventoryservice.event.TrackProductEvent;
import com.truongnhatanh7.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(groupId = "groupInventory", topics = "product.inventory")
public class InventoryListener {
    private final InventoryService inventoryService;

    public InventoryListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaHandler
    public void handleTrackProduct(TrackProductEvent trackProductEvent) {
        inventoryService.handleTrackInventory(trackProductEvent);
    }
}
