package com.truongnhatanh7.productservice.controller;

import com.truongnhatanh7.productservice.dto.request.CategoryRequest;
import com.truongnhatanh7.productservice.dto.response.CategoryResponse;
import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.shared.controller.BaseController;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/category")
@RestController
public class CategoryController extends BaseController<Category, Long, CategoryRequest, CategoryResponse> {
    public CategoryController(BaseService<Category, Long, CategoryRequest, CategoryResponse> service) {
        super(service);
    }
}
