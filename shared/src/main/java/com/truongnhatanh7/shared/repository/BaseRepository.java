package com.truongnhatanh7.shared.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
@NoRepositoryBean
public interface BaseRepository<T, ID>
        extends JpaRepository<T, ID>,
        PagingAndSortingRepository<T, ID>,
        JpaSpecificationExecutor<T> {

}
