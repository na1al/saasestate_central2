package com.saasestate.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.entity.EstateData;
import com.saasestate.app.mappers.EstateMapper;
import com.saasestate.app.repository.EstateDataRepository;
import com.saasestate.app.repository.EstateRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstateService {


    private AddressService addressService;
    private EstateRepository estateRepository;
    private EstateDataRepository estateDataRepository;
    private EstateMapper mapper;

    public EstateService(AddressService addressService, EstateRepository estateRepository, EstateDataRepository estateDataRepository, EstateMapper mapper) {
        this.addressService = addressService;
        this.estateRepository = estateRepository;
        this.estateDataRepository = estateDataRepository;
        this.mapper = mapper;
    }

    public void batchDataInsert(List<EstateData> data) {
        estateDataRepository.batchInsert(data);
        data.clear();
    }

    public EstateData getNextToUpdate() {
        return estateDataRepository.findFirstByStateFalse();
    }

    /**
     * Add new entity or update exist one
     *
     * @param data
     * @throws JsonProcessingException
     */
    public void upsert(EstateData data) throws JsonProcessingException {

        var item = data.data;
        Estate estate = estateRepository
                .findByUserIdAndObjectId(item.userId, item.objectId)
                .orElse(mapper.toEntity(item));

        estate.address = addressService.getOrCreateAddressByCoordinates(item.location.lat, item.location.lng);
        estateRepository.save(estate);

        data.setState(true);
        estateDataRepository.save(data);

    }

}
