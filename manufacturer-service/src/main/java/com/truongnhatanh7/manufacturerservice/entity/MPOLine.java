package com.truongnhatanh7.manufacturerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.truongnhatanh7.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "t_mpo_lines")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MPOLine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mpo_id", nullable = false)
    @JsonIgnore
    private MPO mpo;

    private Long productId;
    private Integer requestQty;
}
