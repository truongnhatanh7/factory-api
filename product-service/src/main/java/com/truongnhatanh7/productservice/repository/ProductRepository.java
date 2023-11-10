package com.truongnhatanh7.productservice.repository;

import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.productservice.entity.Product;
import com.truongnhatanh7.shared.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    @Query("SELECT parentProduct FROM Product p JOIN p.components components JOIN components.id parentProduct WHERE p.id = ?1")
    public List<Product> findComponentsById(Long productId);
}
