package com.springboot.project.guestservice.service;

import com.springboot.project.guestservice.entity.Address;
import com.springboot.project.guestservice.entity.AddressType;
import com.springboot.project.guestservice.entity.Guest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuestIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateGuest() {
        Address address = Address.builder().id(1).addressType(AddressType.HOME).build();
        Guest guest = Guest.builder().guestId("12c9f1e4a1a04878860b588f42d37a6d")
                .guestName("Name").addressDetails(Arrays.asList(address)).build();
        HttpEntity<Guest> entity = new HttpEntity<>(guest);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange("/guests", HttpMethod.POST, entity,
                String.class);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.CREATED.value());
    }

    @Test
    void testCreateGuestBadRequest() {
        Guest guest = Guest.builder().build();
        HttpEntity<Guest> entity = new HttpEntity<>(guest);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange("/guests", HttpMethod.POST, entity,
                String.class);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void testDeleteGuest() {
        testRestTemplate.execute("/guests/12c9f1e4a1a04878860b588f42d37a6d",
                HttpMethod.DELETE,null, null);
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/guests/12c9f1e4a1a04878860b588f42d37a6d", String.class);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
