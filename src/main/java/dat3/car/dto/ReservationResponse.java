package dat3.car.dto;


import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {

	Long id;
	int carId;
	String brand;
	String model;
	Double price;
	//@JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
	LocalDate reservationDate;

	public ReservationResponse(Reservation reservation) {
		this.id = reservation.getId();
		this.carId = reservation.getCar().getId();
		this.brand = reservation.getCar().getBrand();
		this.model = reservation.getCar().getModel();
		this.price=reservation.getCar().getPricePrDay();
		this.reservationDate = reservation.getRentalDate();
	}
}