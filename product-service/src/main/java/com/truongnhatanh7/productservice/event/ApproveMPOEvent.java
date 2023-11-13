package com.truongnhatanh7.productservice.event;

import com.truongnhatanh7.productservice.dto.request.MPOLineRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApproveMPOEvent {
    private Collection<MPOLineRequest> mpoLines;
}
