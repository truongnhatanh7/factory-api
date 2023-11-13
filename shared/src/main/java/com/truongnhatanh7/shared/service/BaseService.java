package com.truongnhatanh7.shared.service;

import com.truongnhatanh7.shared.entity.BaseEntity;
import com.truongnhatanh7.shared.repository.BaseRepository;
import com.truongnhatanh7.shared.specifications.Filter;
import com.truongnhatanh7.shared.specifications.SpecificationFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class BaseService<T extends BaseEntity, ID, TRequest, TResponse> {
    private final BaseRepository<T, ID> repository;

    public abstract T mapRequestToOrm(TRequest tRequest);
    public abstract T mapPatchToOrm(TRequest tRequest, T tInstance);
    public abstract TResponse mapOrmToResponse(T tInstance);

    public Page<T> getAll(int page,
                          int size,
                          String sortBy,
                          boolean desc,
                          String field,
                          String operator,
                          String value,
                          List<String> values
    ) {

        Pageable paging;
        if (sortBy == null) {
            paging = PageRequest.of(page, size);
        } else {
            paging = PageRequest.of(
                    page,
                    size,
                    desc ?
                            Sort.by(sortBy).descending()
                            : Sort.by(sortBy).ascending());
        }
        if (field != null && operator != null) {
            if (values == null && value == null) {
                throw new RuntimeException("bad request");
            }
            Filter filter = values == null ?
                    Filter.builder()
                        .field(field)
                        .operator(operator)
                        .value(value)
                        .build() :
                    Filter.builder()
                        .field(field)
                        .operator(operator)
                        .values(values)
                        .build();
            SpecificationFactory<T> specificationFactory = new SpecificationFactory<T>();
            Specification<T> spec = specificationFactory.create(filter);
            return repository.findAll(spec, paging);
        }
        return repository.findAll(paging);
    }

    public ResponseEntity<T> getById(ID id) {
        Optional<T> entity = repository.findById(id);
        return entity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    public TResponse create(TRequest request) {
        T tInstance = mapRequestToOrm(request);

        repository.save(tInstance);
        return mapOrmToResponse(tInstance);
    }

    public ResponseEntity<TResponse> update(ID id, TRequest tRequest) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            T tInstance = entity.get();
            tInstance = mapPatchToOrm(tRequest, tInstance);
            tInstance.setId((Long) id);
            tInstance = repository.save(tInstance);
            return ResponseEntity.ok(mapOrmToResponse(tInstance));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
