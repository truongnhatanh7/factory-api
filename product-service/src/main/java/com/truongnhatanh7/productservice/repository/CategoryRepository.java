package com.truongnhatanh7.productservice.repository;

import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
    public List<Category> findCategoriesByProductsId(Long productId);
}
