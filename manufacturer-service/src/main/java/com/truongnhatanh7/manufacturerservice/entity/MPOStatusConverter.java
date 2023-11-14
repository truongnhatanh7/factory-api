package com.truongnhatanh7.manufacturerservice.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MPOStatusConverter implements AttributeConverter<MPOStatus, String> {
    @Override
    public String convertToDatabaseColumn(MPOStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public MPOStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(MPOStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
