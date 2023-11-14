package com.truongnhatanh7.productservice.listener;

import com.truongnhatanh7.productservice.dto.request.MPOLineRequest;
import com.truongnhatanh7.productservice.dto.request.ProductRequest;
import com.truongnhatanh7.productservice.entity.Product;
import com.truongnhatanh7.productservice.event.ApproveMPOEvent;
import com.truongnhatanh7.productservice.repository.ProductRepository;
import com.truongnhatanh7.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@KafkaListener(groupId = "productGroup", topics = "mpo.product.topic")
public class ProductListener {
    private final ProductService productService;

    public ProductListener(ProductService productService) {
        this.productService = productService;
    }

    @KafkaHandler
    public void handleApproveMPO(ApproveMPOEvent approveMPOEvent) {
        for (MPOLineRequest mpoLineRequest : approveMPOEvent.getMpoLines()) {
            ProductRequest productRequest = ProductRequest.builder()
                    .qty(mpoLineRequest.getRequestQty())
                    .build();
            productService.increaseQty(
                    mpoLineRequest.getMpoId(),
                    mpoLineRequest.getProductId(),
                    mpoLineRequest.getRequestQty()
            );
        }
    }
}
