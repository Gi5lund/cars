package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends AdminDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	private LocalDate rentalDate;


	public Reservation(Car car, Member member, LocalDate reservationDate)
		{
			this.car = car;
			this.member = member;
			this.rentalDate = reservationDate;
			car.addReservation(this);
			member.addReservation(this);
		}
}