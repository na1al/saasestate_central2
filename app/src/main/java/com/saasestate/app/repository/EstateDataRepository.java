package com.saasestate.app.repository;

import com.saasestate.app.entity.EstateData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateDataRepository extends CrudRepository<EstateData, Long>, EstateDataRepositoryCustom {
    public EstateData findFirstByStateFalse();
}
