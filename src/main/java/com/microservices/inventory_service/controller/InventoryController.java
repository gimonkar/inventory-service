package com.microservices.inventory_service.controller;

import com.microservices.inventory_service.model.InventoryItem;
import com.microservices.inventory_service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody InventoryItem item) {
        service.addItem(item);
        return ResponseEntity.ok("Inventory item added");
    }

    @GetMapping("/all")
    public List<InventoryItem> getAll() {
        return service.getItems();
    }
}
