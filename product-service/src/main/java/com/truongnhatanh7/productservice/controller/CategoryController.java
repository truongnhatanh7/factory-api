package com.truongnhatanh7.productservice.controller;

import com.truongnhatanh7.productservice.dto.request.CategoryRequest;
import com.truongnhatanh7.productservice.dto.response.CategoryResponse;
import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.productservice.service.CategoryService;
import com.truongnhatanh7.shared.controller.BaseController;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/category")
@RestController
public class CategoryController extends BaseController<Category, Long, CategoryRequest, CategoryResponse> {
    private final CategoryService service;
    public CategoryController(CategoryService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/product/{productId}")
    public List<Category> findByProductId(
            @PathVariable(value = "productId") Long productId
    ) {
        return this.service.getByProductId(productId);
    }
}
