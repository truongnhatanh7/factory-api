package com.truongnhatanh7.inventoryservice.repository;

import com.truongnhatanh7.inventoryservice.entity.Inventory;
import com.truongnhatanh7.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends BaseRepository<Inventory, Long> {
}
