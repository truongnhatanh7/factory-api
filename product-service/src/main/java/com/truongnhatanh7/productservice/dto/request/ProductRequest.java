package com.truongnhatanh7.productservice.dto.request;

import com.truongnhatanh7.productservice.entity.Category;
import com.truongnhatanh7.productservice.entity.Product;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String code;
    private String name;
    private String description;
    private Double unitCost;
    private Integer qty;

//    private Set<Product> components;
//    private Set<Category> categories;
}
