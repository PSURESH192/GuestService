package com.springboot.project.guestservice.controller;

import com.springboot.project.guestservice.entity.Guest;
import com.springboot.project.guestservice.model.GuestDetails;
import com.springboot.project.guestservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class GuestControllerImpl implements GuestController {

    @Autowired
    GuestService guestService;

    @Override
    @PostMapping("/guests")
    public ResponseEntity<String> createGuest(@Valid @RequestBody Guest guest) {
        if (Optional.ofNullable(guest).isPresent()) {
            guestService.createGuest(guest);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(guest.getGuestId(), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/guests")
    public ResponseEntity<List<GuestDetails>> getGuests() {
        return new ResponseEntity<>(guestService.getGuests(),HttpStatus.OK);
    }

    @Override
    @GetMapping("/guests/{id}")
    public ResponseEntity<GuestDetails> getGuest(@NotNull @NotBlank @PathVariable("id") String id) {
        return new ResponseEntity<>(guestService.getGuest(id),HttpStatus.OK);
    }

    @Override
    @PutMapping("/guests/{id}")
    public ResponseEntity<GuestDetails> updateGuest(@Valid @NotNull @RequestBody GuestDetails guestDetails,
                                                     @NotNull @NotBlank @PathVariable("id") String id) {
        return new ResponseEntity<>(guestService.updateGuest(guestDetails, id),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/guests/{id}")
    public ResponseEntity<String> deleteGuest(@NotNull @NotBlank @PathVariable("id") String id) {
        return new ResponseEntity<>(guestService.deleteGuest(id),HttpStatus.OK);
    }
}