package com.springboot.project.guestservice.mapper;

import com.springboot.project.guestservice.entity.Address;
import com.springboot.project.guestservice.entity.AddressType;
import com.springboot.project.guestservice.model.AddressDetails;
import com.springboot.project.guestservice.repository.AddressRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class AddressMapperTest {

    @InjectMocks
    AddressMapper addressMapper;

    @Mock
    AddressRepository addressRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertEntityToModel(){
        Address address = Address.builder().addressType(AddressType.HOME)
                .state("Telangana").street("GS Street").city("Hyd").zipcode(560087).build();
        List<Address> addresses = Arrays.asList(address);
        List<AddressDetails> addressDetails = addressMapper.convertEntityToModel(addresses);
        Assert.assertEquals(addressDetails.get(0).getId(),address.getId());
    }

    @Test
    public void testConvertModelToEntity(){
        AddressDetails addressDetails = AddressDetails.builder().addressType(AddressType.HOME.toString())
                .state("Telangana").street("GS Street").city("Hyd").zipcode(560087).build();
        List<AddressDetails> addresses = Arrays.asList(addressDetails);
        Address address = Address.builder().addressType(AddressType.HOME)
                .state("Telangana").street("GS Street").city("Hyd").zipcode(560087).build();
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.of(address));
        List<Address> response = addressMapper.convertModelToEntity(addresses);
        Assert.assertNotNull(response);
    }

}

