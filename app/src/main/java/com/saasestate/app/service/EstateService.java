package com.saasestate.app.service;

import com.saasestate.app.component.geo.GeoApi;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.repository.EstateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstateService {

    private GeoApi geoApi;
    private EstateRepository estateRepository;

    public EstateService(GeoApi geoApi, EstateRepository estateRepository) {
        this.geoApi = geoApi;
        this.estateRepository = estateRepository;
    }

    /**
     * @param estates
     */
    public void batchInsert(List<Estate> estates) {
        estateRepository.batchInsert(estates);
        estates.clear();
    }

    public Optional<Estate> getNextToUpdate() {
        return estateRepository.findFirstByNextToUpdate();
    }

    public void updateMetaData(Estate estate){
       var k = geoApi.reverse(estate.data.location.lat,estate.data.location.lng);
       var c = "a";
    }

}
