package com.truongnhatanh7.productservice.service;

import com.truongnhatanh7.productservice.dto.request.CategoryRequest;
import com.truongnhatanh7.productservice.dto.response.CategoryResponse;
import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.productservice.repository.CategoryRepository;
import com.truongnhatanh7.shared.repository.BaseRepository;
import com.truongnhatanh7.shared.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService<Category, Long, CategoryRequest, CategoryResponse> {
    private CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.categoryRepository = repository;
    }

    @Override
    public Category mapRequestToOrm(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .build();
    }

    @Override
    public Category mapPatchToOrm(CategoryRequest categoryRequest, Category tInstance) {
        return Category.builder()
                .name(categoryRequest.getName() == null ?
                        tInstance.getName() :
                        categoryRequest.getName())
                .build();
    }

    @Override
    public CategoryResponse mapOrmToResponse(Category tInstance) {
        return CategoryResponse.builder()
                .id(tInstance.getId())
                .name(tInstance.getName())
                .build();
    }

    public List<Category> getByProductId(Long productId) {
        return this.categoryRepository.findCategoriesByProductsId(productId);
    }
}
