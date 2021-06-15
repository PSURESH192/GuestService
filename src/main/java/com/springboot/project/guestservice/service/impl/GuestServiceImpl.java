package com.springboot.project.guestservice.service.impl;

import com.springboot.project.guestservice.entity.Guest;
import com.springboot.project.guestservice.exception.IDNotFoundException;
import com.springboot.project.guestservice.mapper.GuestMapper;
import com.springboot.project.guestservice.model.GuestDetails;
import com.springboot.project.guestservice.repository.GuestRepository;
import com.springboot.project.guestservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestMapper guestMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createGuest(Guest guest)
    {
        guest.setGuestId(UUID.randomUUID().toString().replace("-", ""));
        Guest saveGuest = guestRepository.save(guest);
        return saveGuest.getGuestId();

    }

    @Override
    public List<GuestDetails> getGuests() {
        return guestMapper.convertEntityToModel(guestRepository.findAll());
    }

    @Override
    public GuestDetails getGuest(String id) {
        try{
            Optional<Guest> guestOptional = guestRepository.findById(id);
            return guestMapper.convertEntityToModel(guestOptional.isPresent() ? guestOptional.get() : null);
        } catch (Exception ex){
            throw new IDNotFoundException();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GuestDetails updateGuest(GuestDetails guestDetails, String id) {
        try {
            Optional<Guest> guestOptional = guestRepository.findById(id);
            Guest guest = guestRepository.save(guestMapper.convertRequestModelToEntity(guestDetails,
                    guestOptional.isPresent() ? guestOptional.get() : null));
            return guestMapper.convertEntityToModel(guest);
        } catch (Exception ex){
            throw new IDNotFoundException();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String deleteGuest(String id) {
        guestRepository.deleteById(id);
        return id;
    }
}
