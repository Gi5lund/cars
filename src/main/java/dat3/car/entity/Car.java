package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
//-----
@Entity
@Table(name = "car")

public class Car extends AdminDetails
	{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column(name = "car_brand",length = 50,nullable = false)
		private String brand;
		@Column (name = "car_model",length = 60,nullable = false)
		private String model;
		@Column(name = "rental_price_day")
		private double pricePrDay;
		@Column(name = "max_discount")
		private int bestDiscount;
		@OneToMany(mappedBy = "car")
		private Set<Reservation> reservations;


		public Car(String brand, String model, double pricePrDay, int bestDiscount)
			{

				this.brand = brand;
				this.model = model;
				this.pricePrDay = pricePrDay;
				this.bestDiscount = bestDiscount;
			}

		public void addReservation(Reservation reservation){
			if(reservations==null){
				reservations=new HashSet<>();
			}
			reservations.add(reservation);
		}
	}
