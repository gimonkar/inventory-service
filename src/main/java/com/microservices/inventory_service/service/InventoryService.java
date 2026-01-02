package com.microservices.inventory_service.service;

import com.microservices.inventory_service.model.InventoryItem;
import com.microservices.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void addItem(InventoryItem item) {
        repository.save(item);
    }

    public List<InventoryItem> getItems() {
        return repository.findAll();
    }
}
