package com.truongnhatanh7.productservice.controller;

import com.truongnhatanh7.productservice.dto.request.CategoryRequest;
import com.truongnhatanh7.productservice.dto.request.ProductComponentRequest;
import com.truongnhatanh7.productservice.dto.request.ProductRequest;
import com.truongnhatanh7.productservice.dto.response.ProductResponse;
import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.productservice.entity.Product;
import com.truongnhatanh7.productservice.service.ProductService;
import com.truongnhatanh7.shared.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductController extends BaseController<Product, Long, ProductRequest, ProductResponse> {
    private ProductService service;
    public ProductController(ProductService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/{productId}/category")
    public ResponseEntity<Category> addCategory(
            @PathVariable(value = "productId") Long productId,
            @RequestBody CategoryRequest categoryRequest) {
        return this.service.addCategory(productId, categoryRequest);
    }

    @DeleteMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<Void> removeCategory(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "categoryId") Long categoryId
    ) {
        return this.service.removeCategory(productId, categoryId);
    }

    @PostMapping("/{productId}/component")
    public ResponseEntity<ProductResponse> addComponent(
            @PathVariable(value = "productId") Long productId,
            @RequestBody ProductComponentRequest productComponentRequest
    ) {
        return this.service.addComponent(productId, productComponentRequest);
    }

    @DeleteMapping("/{productId}/component/{componentId}")
    public ResponseEntity<Void> removeComponent(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "componentId") Long componentId
    ) {
        return this.service.removeComponent(productId, componentId);
    }

    @GetMapping("/{productId}/components")
    public List<Product> getComponentsOfProduct(
            @PathVariable(value = "productId") Long productId
    ) {
        return this.service.getComponentsOfProduct(productId);
    }
}
