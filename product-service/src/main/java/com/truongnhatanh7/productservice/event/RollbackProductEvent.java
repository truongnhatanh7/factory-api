package com.truongnhatanh7.productservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackProductEvent {
    private Long mpoId;
}
