package com.saasestate.app.service;

import com.saasestate.app.component.geo.GeoApi;
import com.saasestate.app.entity.Address;
import com.saasestate.app.mappers.AddressMapper;
import com.saasestate.app.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


    private GeoApi geoApi;
    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    public AddressService(GeoApi geoApi, AddressMapper addressMapper, AddressRepository addressRepository) {
        this.geoApi = geoApi;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Address getOrCreateAddressByCoordinates(double lat, double lng){

        var response = geoApi.reverse(lat,lng);

        if(response.data.length == 0){
            return null;
        }

        var item = response.data[0];
        Address address = addressRepository.findById(item.getId()).orElse(null);

        if(address == null){
            address = addressRepository.save(addressMapper.toEntity(item));
        }

        return  address;
    }

}
