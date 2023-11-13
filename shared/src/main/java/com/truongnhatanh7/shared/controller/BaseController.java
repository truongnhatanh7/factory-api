package com.truongnhatanh7.shared.controller;

import com.truongnhatanh7.shared.entity.BaseEntity;
import com.truongnhatanh7.shared.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public abstract class BaseController<T extends BaseEntity, ID, TRequest, TResponse> {

//    private final BaseRepository<T, ID> repository;
    private final BaseService<T, ID, TRequest, TResponse> service;
    @GetMapping(value = "/findAll")
    public Page<T> getAll(@RequestParam(name = "page") int page,
                          @RequestParam(name = "size") int size,
                          @RequestParam(name = "sortBy", required = false) String sortBy,
                          @RequestParam(name = "desc", required = false, defaultValue = "false") boolean desc,
                          @RequestParam(name = "field", required = false) String field,
                          @RequestParam(name = "operator", required = false) String operator,
                          @RequestParam(name = "value", required = false) String value,
                          @RequestParam(name = "values", required = false) List<String> values
                          ) {
        return service.getAll(page, size, sortBy, desc, field, operator, value, values);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        return service.getById(id);
    }

    @PostMapping("/create")
    public TResponse create(@RequestBody TRequest request) {
        return service.create(request);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<TResponse> update(@PathVariable ID id, @RequestBody TRequest tRequest) {
        return service.update(id, tRequest);
    }

    @DeleteMapping("/truncate/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        return service.delete(id);
    }
}
