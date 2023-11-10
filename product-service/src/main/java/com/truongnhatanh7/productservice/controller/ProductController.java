package com.truongnhatanh7.productservice.controller;

import com.truongnhatanh7.productservice.dto.request.CategoryRequest;
import com.truongnhatanh7.productservice.dto.request.ProductRequest;
import com.truongnhatanh7.productservice.dto.response.ProductResponse;
import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.productservice.entity.Product;
import com.truongnhatanh7.productservice.service.ProductService;
import com.truongnhatanh7.shared.controller.BaseController;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
