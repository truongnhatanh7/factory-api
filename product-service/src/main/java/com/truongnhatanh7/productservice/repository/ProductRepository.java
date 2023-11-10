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
    @Query(value = "SELECT * FROM t_product p LEFT JOIN t_product_component pc ON pc.components_id=p.id WHERE pc.product_id=?1", nativeQuery = true)
    public List<Product> findComponentsById(Long productId);
}
