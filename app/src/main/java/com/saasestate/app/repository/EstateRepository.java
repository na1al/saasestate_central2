package com.saasestate.app.repository;


import com.saasestate.app.entity.Estate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstateRepository extends CrudRepository<Estate, Long>, PagingAndSortingRepository<Estate, Long>, EstateRepositoryCustom {

    @Query(nativeQuery = true, value = "SELECT * FROM estate e where e.old_data_hash <> e.data_hash or e.old_data_hash IS NULL LIMIT 1")
    public Optional<Estate> findFirstByNextToUpdate();

}
