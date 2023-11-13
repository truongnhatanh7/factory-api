package com.truongnhatanh7.manufacturerservice.event;

import com.truongnhatanh7.manufacturerservice.dto.request.MPOLineRequest;
import com.truongnhatanh7.manufacturerservice.entity.MPOLine;
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
