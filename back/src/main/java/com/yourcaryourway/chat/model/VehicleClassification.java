package com.yourcaryourway.chat.model;

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
@Table(name = "vehicle_classification")
public class VehicleClassification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vehicle_classification_id")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_category")
	private VehicleCategory vehicleCategory;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_type")
	private VehicleType vehicleType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_transmission_drive")
	private VehicleTransmissionDrive vehicleTransmissionDrive;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_fuel_air_cond")
	private VehicleFuelAirCond vehicleFuelAirCond;
	
}
