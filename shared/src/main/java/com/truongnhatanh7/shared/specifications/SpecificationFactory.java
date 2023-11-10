package com.truongnhatanh7.shared.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationFactory<T> {
    private Object castToRequiredType(Class<?> fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if(fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if(fieldType.isAssignableFrom(Long.class)) {
            return Long.valueOf(value);
        } else if(fieldType.isAssignableFrom(String.class)){
            return value;
        }

        return null;
    }
    public Specification<T> create(Filter filter) {
        switch (filter.getOperator()) {
            case FilterOperator.EQUALS:
                return new Specification<T>() {
                    @Override
                    public Predicate toPredicate(@NonNull Root<T> root,
                                                 @NonNull CriteriaQuery<?> query,
                                                 @NonNull CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.equal(root.get(filter.getField()),
                                castToRequiredType((root.get(filter.getField()).getJavaType()),
                                        filter.getValue()));
                    }
                };

            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }
}
