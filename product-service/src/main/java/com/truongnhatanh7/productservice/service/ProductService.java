package com.truongnhatanh7.productservice.service;

import com.truongnhatanh7.productservice.dto.request.CategoryRequest;
import com.truongnhatanh7.productservice.dto.request.ProductRequest;
import com.truongnhatanh7.productservice.dto.response.ProductResponse;
import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.productservice.entity.Product;
import com.truongnhatanh7.productservice.repository.CategoryRepository;
import com.truongnhatanh7.productservice.repository.ProductRepository;
import com.truongnhatanh7.shared.repository.BaseRepository;
import com.truongnhatanh7.shared.service.BaseService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, Long, ProductRequest, ProductResponse> {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        super(productRepository);
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product mapRequestToOrm(ProductRequest productRequest) {
        return Product.builder()
                .code(productRequest.getCode())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .qty(productRequest.getQty())
                .unitCost(productRequest.getUnitCost())
                .components(productRequest.getComponents())
                .categories(productRequest.getCategories())
                .build();
    }

    @Override
    public Product mapPatchToOrm(ProductRequest productRequest, Product tInstance) {
        return Product.builder()
                .code(productRequest.getCode() == null ?
                        tInstance.getCode() :
                        productRequest.getCode())
                .name(productRequest.getName() == null ?
                        tInstance.getName() :
                        productRequest.getName())
                .description(productRequest.getDescription() == null ?
                        tInstance.getDescription() :
                        productRequest.getDescription())
                .qty(productRequest.getQty() == null ?
                        tInstance.getQty() :
                        productRequest.getQty())
                .unitCost(productRequest.getUnitCost() == null ?
                        tInstance.getUnitCost() :
                        productRequest.getUnitCost())
                .components(productRequest.getComponents() == null ?
                        tInstance.getComponents() :
                        productRequest.getComponents())
                .categories(productRequest.getCategories() == null ?
                        tInstance.getCategories() :
                        productRequest.getCategories())
                .build();
    }

    @Override
    public ProductResponse mapOrmToResponse(Product tInstance) {
        return ProductResponse.builder()
                .id(tInstance.getId())
                .code(tInstance.getCode())
                .name(tInstance.getName())
                .description(tInstance.getDescription())
                .qty(tInstance.getQty())
                .unitCost(tInstance.getUnitCost())
                .components(tInstance.getComponents())
                .categories(tInstance.getCategories())
                .build();
    }

    public ResponseEntity<Category> addCategory(
            Long productId,
            CategoryRequest categoryRequest) {
        Category category = productRepository.findById(productId).map(product -> {
            if (categoryRepository.existsById(categoryRequest.getId())) {
                Category _category = categoryRepository
                        .findById(categoryRequest.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("not found"));
                product.addCategory(_category);
                productRepository.save(product);
                return _category;
            }

            Category _category = Category.builder()
                    .name(categoryRequest.getName())
                    .build();
            product.addCategory(_category);
            return categoryRepository.save(_category);
        }).orElseThrow(() -> new ResourceNotFoundException("not fouud"));
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
