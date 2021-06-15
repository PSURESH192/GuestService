package com.springboot.project.guestservice.service;


import com.springboot.project.guestservice.entity.Guest;
import com.springboot.project.guestservice.model.GuestDetails;

import java.util.List;

public interface GuestService {

   String createGuest(Guest guest);

   List<GuestDetails> getGuests();

   GuestDetails getGuest(String id);

   GuestDetails updateGuest(GuestDetails guestDetails , String id);

   String deleteGuest(String id);

}
