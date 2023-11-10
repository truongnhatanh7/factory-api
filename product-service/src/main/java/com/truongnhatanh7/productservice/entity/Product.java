package com.truongnhatanh7.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.truongnhatanh7.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private Double unitCost;
    private Integer qty;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "t_product_component")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Product> components;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @EqualsAndHashCode.Exclude
    private Set<Category> categories;

    public void addComponent(Product product) {
        this.components.add(product);
    }

    public void removeComponent(Long componentId) {
        Product component = this.components.stream()
                .filter(c -> Objects.equals(c.getId(), componentId))
                .findFirst()
                .orElse(null);
        if (component != null) {
            this.components.remove(component);
        }
    }


    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(Long categoryId) {
        Category category = this.categories.stream()
                .filter(c -> Objects.equals(c.getId(), categoryId))
                .findFirst()
                .orElse(null);
        if (category != null) {
            this.categories.remove(category);
            category.getProducts().remove(this);
        }
    }

}
