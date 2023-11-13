package com.truongnhatanh7.manufacturerservice.dto.request;

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
public class MPORequest {
//    private Long id;
    private Collection<MPOLine> mpoLines;
}
