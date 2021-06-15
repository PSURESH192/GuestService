package com.springboot.project.guestservice.mapper;

import com.springboot.project.guestservice.entity.Address;
import com.springboot.project.guestservice.entity.AddressType;
import com.springboot.project.guestservice.model.AddressDetails;
import com.springboot.project.guestservice.repository.AddressRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    @Autowired
    AddressRepository addressRepository;

    public List<AddressDetails> convertEntityToModel(List<Address> addresses) {
        List<AddressDetails> addressDetails = new ArrayList<>();
        if(Optional.ofNullable(addresses).isPresent() && !addresses.isEmpty())
        {
            addressDetails = addresses.stream().filter(Objects::nonNull)
                    .map(this::convertEntityToModel)
                    .collect(Collectors.toList());
        }
        return addressDetails;
    }

    public AddressDetails convertEntityToModel(Address address) {
        return AddressDetails.builder().id(address.getId()).addressType(address.getAddressType().toString())
                .street(address.getStreet())
                .city(address.getCity()).zipcode(address.getZipcode())
                .state(address.getState())
                .build();
    }

    public List<Address> convertModelToEntity(List<AddressDetails> addressDetails) {
        List<Address> addresses = new ArrayList<>();
        if(Optional.ofNullable(addressDetails).isPresent() && !addressDetails.isEmpty())
        {
            addresses = addressDetails.stream().filter(Objects::nonNull)
                    .map(this::convertModelToEntity)
                    .collect(Collectors.toList());
        }
        return addresses;
    }

    private Address convertModelToEntity(AddressDetails addressDetails) {
        Optional<Address> optionalAddress = addressRepository.findById(addressDetails.getId());

        Address address = optionalAddress.isPresent() ? optionalAddress.get() : null;

        if (StringUtils.isNotBlank(addressDetails.getAddressType())) {
            address.setAddressType(AddressType.valueOf(addressDetails.getAddressType()));
        }

        if (StringUtils.isNotBlank(addressDetails.getStreet())) {
            address.setStreet(addressDetails.getStreet());
        }

        if (StringUtils.isNotBlank(addressDetails.getCity())) {
            address.setCity(addressDetails.getCity());
        }
        if (StringUtils.isNotBlank(addressDetails.getState())) {
            address.setState(addressDetails.getState());
        }

        if (Optional.ofNullable(addressDetails.getZipcode()).isPresent()) {
            address.setZipcode(addressDetails.getZipcode());
        }

        return addressRepository.save(address);
    }

}
