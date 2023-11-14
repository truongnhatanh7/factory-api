package com.truongnhatanh7.manufacturerservice.entity;

import com.truongnhatanh7.manufacturerservice.dto.request.MPOLineRequest;
import com.truongnhatanh7.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.kafka.common.errors.ResourceNotFoundException;

import java.time.LocalDate;
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
    @EqualsAndHashCode.Exclude
    private Collection<MPOLine> mpoLines;
    private MPOStatus status;
    private LocalDate requestDate;
    private LocalDate approveDate;

    public MPOLine addMpoLine(MPOLineRequest mpoLineRequest) {
        MPOLine mpoLine = MPOLine.builder()
                .mpo(this)
                .productId(mpoLineRequest.getProductId())
                .requestQty(mpoLineRequest.getRequestQty())
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
