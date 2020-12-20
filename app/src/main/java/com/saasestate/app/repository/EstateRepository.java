package com.saasestate.app.repository;

import com.saasestate.app.entity.Estate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstateRepository extends CrudRepository<Estate, Long>, PagingAndSortingRepository<Estate, Long> {
    public Optional<Estate> findByUserIdAndObjectId(int userId, int objectId);
}
