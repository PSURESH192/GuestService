package com.springboot.project.guestservice.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestDetails {

    private String guestId;
    private String guestName;
    private Long contact;

    private List<AddressDetails> addressDetails;
}
