package com.truongnhatanh7.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.truongnhatanh7.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "t_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "categories")
    @JsonIgnore
    private Set<Product> products;
}
