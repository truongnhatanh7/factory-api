package com.truongnhatanh7.shared.specifications;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filter {
    private String field;
    private String operator;
    private String value;
    private List<String> values;
}

