package com.springboot.project.guestservice.service;

import com.springboot.project.guestservice.entity.Guest;
import com.springboot.project.guestservice.exception.IDNotFoundException;
import com.springboot.project.guestservice.mapper.AddressMapper;
import com.springboot.project.guestservice.mapper.GuestMapper;
import com.springboot.project.guestservice.model.GuestDetails;
import com.springboot.project.guestservice.repository.AddressRepository;
import com.springboot.project.guestservice.repository.GuestRepository;
import com.springboot.project.guestservice.service.impl.GuestServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class GuestServiceTest {

    @InjectMocks
    GuestServiceImpl guestService;

    @Mock
    GuestRepository guestRepository;

    @Mock
    GuestMapper guestMapper;

    @Mock
    AddressRepository addressRepository;

    @Mock
    AddressMapper addressMapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateGuest(){
        String uuid = UUID.randomUUID().toString();
        Guest guest = Guest.builder().guestId(uuid).guestName("Raj").contact(9879879876L).build();
        Mockito.when(guestRepository.save(guest)).thenReturn(guest);
        String guestId = guestService.createGuest(guest);
        Assert.assertNotNull(guestId);
    }

    @Test
    public void testGetGuests(){
        String uuid = UUID.randomUUID().toString();
        Guest guest = Guest.builder().guestId(uuid).guestName("Raj").contact(9879879876L).build();
        List<Guest> guests = Arrays.asList(guest);
        List<GuestDetails> guestDetails = new ArrayList<>();
        Mockito.when(guestRepository.findAll()).thenReturn(guests);
        Mockito.when(guestMapper.convertEntityToModel(Mockito.anyList())).thenReturn(guestDetails);
        Assert.assertNotNull(guestService.getGuests());
    }

    @Test
    public void testGetGuest(){
        String uuid = UUID.randomUUID().toString();
        Guest guest = Guest.builder().guestId(uuid).guestName("Raj").contact(9879879876L).build();
        GuestDetails guestDetails = GuestDetails.builder().guestId(uuid)
                .guestName(guest.getGuestName()).contact(guest.getContact()).build();
        Mockito.when(guestRepository.findById(guest.getGuestId())).thenReturn(Optional.of(guest));
        Mockito.when(guestMapper.convertEntityToModel(guest)).thenReturn(guestDetails);
        guestDetails = guestService.getGuest(guest.getGuestId());
        Assert.assertEquals(guest.getGuestId(),guestDetails.getGuestId());
        Assert.assertEquals(guest.getGuestName(),guestDetails.getGuestName());
    }

    @Test(expected = IDNotFoundException.class)
    public void testGetGuestException(){
        String uuid = UUID.randomUUID().toString();
        Guest guest = Guest.builder().guestId(uuid).guestName("Raj").contact(9879879876L).build();
        GuestDetails guestDetails = GuestDetails.builder().guestId(uuid)
                .guestName(guest.getGuestName()).contact(guest.getContact()).build();
        Mockito.when(guestRepository.findById(guest.getGuestId())).thenThrow(IDNotFoundException.class);
        Mockito.when(guestMapper.convertEntityToModel(guest)).thenThrow(new IDNotFoundException());
        guestDetails = guestService.getGuest(guest.getGuestId());
        Assert.assertEquals(guest.getGuestId(),guestDetails.getGuestId());
        Assert.assertEquals(guest.getGuestName(),guestDetails.getGuestName());
    }

    @Test
    public void testUpdateGuest(){
        String uuid = UUID.randomUUID().toString();
        Guest guest = Guest.builder().guestId(uuid).guestName("Raj").contact(9879879876L).build();
        GuestDetails guestDetails = GuestDetails.builder().guestId(uuid)
                .guestName(guest.getGuestName()).contact(guest.getContact()).build();
        Mockito.when(guestRepository.findById(guest.getGuestId())).thenReturn(Optional.of(guest));
        Mockito.when(guestMapper.convertRequestModelToEntity(guestDetails,guest)).thenReturn(guest);
        Mockito.when(guestRepository.save(Mockito.any())).thenReturn(guest);
        Mockito.when(guestMapper.convertEntityToModel(guest)).thenReturn(guestDetails);
        guestDetails = guestService.updateGuest(guestDetails, guest.getGuestId());
        Assert.assertEquals(guest.getGuestId(),guestDetails.getGuestId());
        Assert.assertEquals(guest.getGuestName(),guestDetails.getGuestName());
    }

    @Test(expected = IDNotFoundException.class)
    public void testUpdateHotelException(){
        String uuid = UUID.randomUUID().toString();
        Guest guest = Guest.builder().guestId(uuid).guestName("Raj").contact(9879879876L).build();
        GuestDetails guestDetails = GuestDetails.builder().guestId(uuid)
                .guestName(guest.getGuestName()).contact(guest.getContact()).build();
        Mockito.when(guestRepository.findById(guest.getGuestId())).thenThrow(IDNotFoundException.class);
        Mockito.when(guestMapper.convertRequestModelToEntity(guestDetails,guest)).thenReturn(guest);
        Mockito.when(guestRepository.save(Mockito.any())).thenReturn(guest);
        Mockito.when(guestMapper.convertEntityToModel(guest)).thenReturn(guestDetails);
        guestDetails = guestService.updateGuest(guestDetails, guest.getGuestId());
        Assert.assertEquals(guest.getGuestId(),guestDetails.getGuestId());
        Assert.assertEquals(guest.getGuestName(),guestDetails.getGuestName());
    }

    @Test
    public void testDeleteGuest(){
        Assert.assertNotNull(guestService.deleteGuest(UUID.randomUUID().toString()));
    }
}
