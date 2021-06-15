package com.springboot.project.guestservice.mapper;

import com.springboot.project.guestservice.entity.Guest;
import com.springboot.project.guestservice.model.GuestDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GuestMapper{

    @Autowired
    private AddressMapper addressMapper;

    public List<GuestDetails> convertEntityToModel(List<Guest> guests) {
        List<GuestDetails> guestDetails = new ArrayList<>();
        if(Optional.ofNullable(guests).isPresent() && !guests.isEmpty())
        {
            guestDetails = guests.stream().filter(Objects::nonNull)
                    .map(this::convertEntityToModel)
                    .collect(Collectors.toList());
        }
        return guestDetails;
    }

    public GuestDetails convertEntityToModel(Guest guest) {
        return GuestDetails.builder()
                .guestId(guest.getGuestId())
                .guestName(guest.getGuestName())
                .contact(guest.getContact())
                .addressDetails(addressMapper.convertEntityToModel(guest.getAddressDetails())).build();
    }

    public Guest convertRequestModelToEntity(GuestDetails guestDetails, Guest guest) {

        if (Optional.ofNullable(guestDetails).isPresent()) {

            if (StringUtils.isNotBlank(guestDetails.getGuestId())) {
                guest.setGuestId(guestDetails.getGuestId());
            }

            if (StringUtils.isNotBlank(guestDetails.getGuestName())) {
                guest.setGuestName(guestDetails.getGuestName());
            }

            if (Optional.ofNullable(guestDetails.getContact()).isPresent()) {
                guest.setContact(guestDetails.getContact());
            }

            guest.setAddressDetails(addressMapper.convertModelToEntity(guestDetails.getAddressDetails()));
        }
        return guest;
    }
}
