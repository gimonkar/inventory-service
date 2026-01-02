package com.microservices.inventory_service.repository;

import com.microservices.inventory_service.model.InventoryItem;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryRepository {

    private final DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "Inventory";

    public InventoryRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(InventoryItem item) {
        Map<String, AttributeValue> map = new HashMap<>();
        map.put("id", AttributeValue.fromS(item.getId()));
        map.put("productName", AttributeValue.fromS(item.getProductName()));
        map.put("quantity", AttributeValue.fromN(item.getQuantity().toString()));

        dynamoDbClient.putItem(PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(map)
                .build());
    }

    public List<InventoryItem> findAll() {
        ScanResponse response = dynamoDbClient.scan(
                ScanRequest.builder().tableName(TABLE_NAME).build()
        );

        return response.items().stream().map(item ->
                new InventoryItem(
                        item.get("id").s(),
                        item.get("productName").s(),
                        Integer.parseInt(item.get("quantity").n())
                )
        ).toList();
    }
}
