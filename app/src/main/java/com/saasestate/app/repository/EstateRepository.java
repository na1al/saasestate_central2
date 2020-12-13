package com.saasestate.app.repository;


import com.saasestate.app.entity.Estate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepository extends CrudRepository<Estate, Long>, PagingAndSortingRepository<Estate, Long>, EstateRepositoryCustom {



}
