package com.truongnhatanh7.manufacturerservice.dto.request;

import com.truongnhatanh7.manufacturerservice.entity.MPO;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MPOLineRequest {
    private Long mpoId;
    private Long productId;
    private Integer requestQty;
}
