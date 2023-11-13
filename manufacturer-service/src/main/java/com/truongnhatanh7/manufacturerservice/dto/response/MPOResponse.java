package com.truongnhatanh7.manufacturerservice.dto.response;

import com.truongnhatanh7.manufacturerservice.entity.MPO;
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
public class MPOResponse {
    private Long id;
    private Collection<MPOLine> mpoLines;
}
