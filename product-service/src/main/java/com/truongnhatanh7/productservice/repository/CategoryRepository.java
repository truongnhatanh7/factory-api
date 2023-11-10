package com.truongnhatanh7.productservice.repository;

import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
