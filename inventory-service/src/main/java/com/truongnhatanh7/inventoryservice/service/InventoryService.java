package com.truongnhatanh7.inventoryservice.service;

import com.truongnhatanh7.inventoryservice.dto.InventoryRequest;
import com.truongnhatanh7.inventoryservice.dto.InventoryResponse;
import com.truongnhatanh7.inventoryservice.entity.Inventory;
import com.truongnhatanh7.inventoryservice.event.CompleteMPOEvent;
import com.truongnhatanh7.inventoryservice.event.RollbackProductEvent;
import com.truongnhatanh7.inventoryservice.event.TrackProductEvent;
import com.truongnhatanh7.inventoryservice.repository.InventoryRepository;
import com.truongnhatanh7.shared.repository.BaseRepository;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class InventoryService extends BaseService<Inventory, Long, InventoryRequest, InventoryResponse> {
    private InventoryRepository inventoryRepository;
    private KafkaTemplate<String, Object> kafkaTemplate;
    public InventoryService(
            InventoryRepository repository,
            KafkaTemplate<String, Object> kafkaTemplate
    ) {
        super(repository);
        this.inventoryRepository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Inventory mapRequestToOrm(InventoryRequest inventoryRequest) {
        return Inventory.builder()
                .totalIn(inventoryRequest.getTotalIn())
                .totalOut(inventoryRequest.getTotalOut())
                .productId(inventoryRequest.getProductId())
                .createdAt(inventoryRequest.getCreatedAt())
                .build();
    }

    @Override
    public Inventory mapPatchToOrm(InventoryRequest inventoryRequest, Inventory tInstance) {
        return Inventory.builder()
                .totalIn(inventoryRequest.getTotalIn() == null ?
                        tInstance.getTotalIn() :
                        inventoryRequest.getTotalIn())
                .totalOut(inventoryRequest.getTotalOut() == null ?
                        tInstance.getTotalOut() :
                        inventoryRequest.getTotalOut())
                .productId(inventoryRequest.getProductId() == null ?
                        tInstance.getProductId() :
                        inventoryRequest.getProductId())
                .createdAt(inventoryRequest.getCreatedAt() == null ?
                        tInstance.getCreatedAt() :
                        inventoryRequest.getCreatedAt())
                .build();
    }

    @Override
    public InventoryResponse mapOrmToResponse(Inventory tInstance) {
        return InventoryResponse.builder()
                .totalIn(tInstance.getTotalIn())
                .totalOut(tInstance.getTotalOut())
                .productId(tInstance.getProductId())
                .createdAt(tInstance.getCreatedAt())
                .build();
    }

    public InventoryResponse filterInDateRange(LocalDate from, LocalDate to) {
        // TODO: Implement filter in date range
        return InventoryResponse.builder().build();
    }
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void handleTrackInventory(TrackProductEvent trackProductEvent) {
        try {
            Inventory inventory = Inventory.builder()
                    .createdAt(LocalDate.now())
                    .productId(trackProductEvent.getProductId())
                    .totalIn(0)
                    .totalOut(0)
                    .build();
            if (trackProductEvent.getIsIncrement()) {
                inventory.setTotalIn(trackProductEvent.getRequestQty());
            } else {
                inventory.setTotalOut(trackProductEvent.getRequestQty());
            }

            inventoryRepository.save(inventory);
            kafkaTemplate.send(
                    "complete.mpo",
                    new CompleteMPOEvent(trackProductEvent.getMpoId()));
        } catch (Exception e) {
            // Send rollback to mpo
            kafkaTemplate.send(
                    "rollback.product",
                    RollbackProductEvent
                    .builder()
                        .isIncrement(trackProductEvent.getIsIncrement())
                        .requestQty(trackProductEvent.getRequestQty())
                        .productId(trackProductEvent.getProductId())
                        .mpoId(trackProductEvent.getMpoId())
                        .build());
        }

    }
}
