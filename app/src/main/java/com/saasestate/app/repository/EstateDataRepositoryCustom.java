package com.saasestate.app.repository;

import com.saasestate.app.entity.EstateData;

import java.util.List;

public interface EstateDataRepositoryCustom {

    public int[] batchInsert(List<EstateData> data);

}
