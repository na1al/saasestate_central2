package com.saasestate.app.repository;

import com.saasestate.app.entity.Estate;

import java.util.List;

public interface EstateRepositoryCustom {
    public int[] batchInsert(List<Estate> estates);
}
