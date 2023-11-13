package com.truongnhatanh7.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MPOLineRequest {
    private Long mpoId;
    private Long productId;
    private Integer requestQty;
}
