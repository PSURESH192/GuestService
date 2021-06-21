package com.springboot.project.guestservice;

import com.springboot.project.guestservice.controller.GuestController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class GuestServiceApplicationTests {

	@Autowired
	GuestController guestController;

	@BeforeEach
	void setup() {
		RestAssuredMockMvc.standaloneSetup(guestController);
	}

	@Test
	public void testController(){
		Assert.assertNotNull(guestController);
	}

}
