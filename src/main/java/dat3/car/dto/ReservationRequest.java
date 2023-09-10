package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {

	int carId;
	String userName;
	@JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
	LocalDate date;

	public ReservationRequest(int carId, String userName, LocalDate date)
		{
			this.carId = carId;
			this.userName = userName;
			this.date = date;
		}

}