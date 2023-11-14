package com.truongnhatanh7.manufacturerservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackProductEvent {
    private Long mpoId;
}
