package com.truongnhatanh7.inventoryservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackProductEvent {
    private Long mpoId;
    private Long productId;
    private Integer requestQty;
    private Boolean isIncrement;
}
