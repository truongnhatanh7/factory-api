package com.truongnhatanh7.manufacturerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private String code;
    private String name;
    private String description;
    private Double unitCost;
    private Integer qty;
}
