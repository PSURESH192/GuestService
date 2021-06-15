package com.springboot.project.guestservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guest")
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Guest {

	@Id
	@Column(name = "guest_Id")
	private String guestId;

	@NotBlank(message = "Guest Name is mandatory")
	@Column(name = "guest_name")
	private String guestName;

	@Column(name = "contact")
	private Long contact;

	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "guest_Id", referencedColumnName = "guest_Id")
	private List<Address> addressDetails;
}