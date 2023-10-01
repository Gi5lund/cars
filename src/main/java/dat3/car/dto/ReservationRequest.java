package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.car.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ReservationRequest {

	int carId;
	String username;
	@JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
	LocalDate date;

	public ReservationRequest(int carId, String userName, LocalDate date)
		{
			this.carId = carId;
			this.username = userName;
			this.date = date;
		}

}