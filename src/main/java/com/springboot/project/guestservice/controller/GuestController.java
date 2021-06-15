package com.springboot.project.guestservice.controller;

import com.springboot.project.guestservice.entity.Guest;
import com.springboot.project.guestservice.model.GuestDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface GuestController {

    @ApiOperation(value = "Create Guest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createGuest(@Valid @RequestBody Guest guest);

    @ApiOperation(value = "Get All Guests", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GuestDetails>> getGuests();

    @ApiOperation(value = "Get Guest Details By ID", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GuestDetails> getGuest(@NotNull @NotBlank @PathVariable("id") String id);

    @ApiOperation(value = "Update Guest By ID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GuestDetails> updateGuest(@RequestBody GuestDetails hotelRequest, @NotNull @NotBlank @PathVariable("id") String id);

    @ApiOperation(value = "Delete Guest By ID", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteGuest(@NotNull @NotBlank @PathVariable("id") String id);
}
