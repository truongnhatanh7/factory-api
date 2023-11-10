package com.truongnhatanh7.productservice.repository;

import com.truongnhatanh7.productservice.entity.Product;
import com.truongnhatanh7.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
