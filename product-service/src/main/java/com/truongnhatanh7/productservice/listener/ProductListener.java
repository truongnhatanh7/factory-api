package com.truongnhatanh7.productservice.listener;

import com.truongnhatanh7.productservice.dto.response.ProductResponse;
import com.truongnhatanh7.productservice.entity.Product;
import com.truongnhatanh7.productservice.event.GetProductByIDEvent;
import com.truongnhatanh7.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "productGroup", topics = "product")
@RequiredArgsConstructor
public class ProductListener {
    private ProductRepository productRepository;

    @KafkaHandler
    @SendTo
    public ProductResponse handleGetProduct(GetProductByIDEvent getProductByIDEvent) {
        Product product = productRepository
                .findById(getProductByIDEvent.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .qty(product.getQty())
                .unitCost(product.getUnitCost())
                .build();
        return response;
    }
}
