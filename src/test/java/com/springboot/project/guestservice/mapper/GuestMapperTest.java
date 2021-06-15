package com.springboot.project.guestservice.mapper;

import com.springboot.project.guestservice.entity.Guest;
import com.springboot.project.guestservice.model.AddressDetails;
import com.springboot.project.guestservice.model.GuestDetails;
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
import java.util.UUID;

@RunWith(SpringRunner.class)
public class GuestMapperTest {

    @InjectMocks
    GuestMapper guestMapper;

    @Mock
    AddressMapper addressMapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertEntityToModel(){
        Guest guest = Guest.builder().guestId(UUID.randomUUID().toString()).guestName("Raj").contact(9879879876L).build();
        List<Guest> guests = Arrays.asList(guest);
        Mockito.when(addressMapper.convertEntityToModel(Mockito.anyList())).thenReturn(Arrays.asList(AddressDetails.builder().build()));
        List<GuestDetails> guestDetails = guestMapper.convertEntityToModel(guests);
        Assert.assertEquals(guest.getGuestId(),guestDetails.get(0).getGuestId());
    }

    @Test
    public void testConvertRequestModelToEntity(){
        Guest guest = Guest.builder().guestId(UUID.randomUUID().toString()).guestName("Raj").contact(9879879876L).build();
        GuestDetails guestDetails = GuestDetails.builder().guestId(guest.getGuestId()).guestName(guest.getGuestName()).contact(guest.getContact())
                .build();
        Guest guestResponse = guestMapper.convertRequestModelToEntity(guestDetails, guest);
        Assert.assertEquals(guest.getGuestId(),guestResponse.getGuestId());
    }
}
