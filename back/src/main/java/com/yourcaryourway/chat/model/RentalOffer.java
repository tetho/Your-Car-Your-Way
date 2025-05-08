package com.yourcaryourway.chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rental_offer")
public class RentalOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rental_offer_id")
	private Integer id;

	@Column(name="start_date_time")
	private LocalDateTime startDateTime;
	
	@Column(name="end_date_time")
	private LocalDateTime endDateTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name="vehicle_category")
	private VehicleCategory vehicleCategory;
	
	private float price;
		
}
