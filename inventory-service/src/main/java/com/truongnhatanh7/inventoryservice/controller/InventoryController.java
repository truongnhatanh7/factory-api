package com.truongnhatanh7.inventoryservice.controller;

import com.truongnhatanh7.inventoryservice.dto.InventoryRequest;
import com.truongnhatanh7.inventoryservice.dto.InventoryResponse;
import com.truongnhatanh7.inventoryservice.entity.Inventory;
import com.truongnhatanh7.inventoryservice.service.InventoryService;
import com.truongnhatanh7.shared.controller.BaseController;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController extends BaseController<Inventory, Long, InventoryRequest, InventoryResponse> {
    private final InventoryService inventoryService;
    public InventoryController(InventoryService service) {
        super(service);
        this.inventoryService = service;
    }

    @GetMapping("/date-range")
    public InventoryResponse findInDateRange(LocalDate from, LocalDate to) {
        return this.inventoryService.filterInDateRange(from, to);
    }
}
