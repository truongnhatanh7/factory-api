package com.truongnhatanh7.manufacturerservice.entity;

import com.truongnhatanh7.manufacturerservice.dto.request.MPOLineRequest;
import com.truongnhatanh7.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;

import java.util.Collection;
import java.util.Objects;

@Data
@Entity
@Table(name = "t_mpos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MPO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "mpo")
    private Collection<MPOLine> mpoLines;

    public MPOLine addMpoLine(MPOLineRequest mpoLineRequest) {
        MPOLine mpoLine = MPOLine.builder()
                .mpo(this)
                .requestQty(mpoLineRequest.getRequestQty())
                .isApproved(mpoLineRequest.getIsApproved())
                .requestDate(mpoLineRequest.getRequestDate())
                .approveDate(mpoLineRequest.getApproveDate())
                .build();

        this.mpoLines.add(mpoLine);
        return mpoLine;
    }

    public void removeMpoLine(Long mpoLineId) {
        MPOLine mpoLine = this.mpoLines.stream()
                .filter(l -> Objects.equals(l.getId(), mpoLineId))
                .findFirst()
                .orElse(null);
        if (mpoLine != null) {
            this.mpoLines.remove(mpoLine);
        }
    }
}
