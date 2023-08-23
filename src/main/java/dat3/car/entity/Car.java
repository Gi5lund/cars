package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
//-----
@Entity
@Table(name = "car")

public class Car
	{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int ID;
		@Column(name = "car_brand",length = 50)
		private String brand;
		@Column (name = "car_model",length = 60)
		private String model;
		@Column(name = "rental_price_day")
		private double pricePrDay;
		@Column(name = "max_discount")
		private int bestDiscount;
		@UpdateTimestamp
		private LocalDateTime lastEdited;
		@CreationTimestamp
		private LocalDateTime created;

		public Car(String brand, String model, double pricePrDay, int bestDiscount)
			{

				this.brand = brand;
				this.model = model;
				this.pricePrDay = pricePrDay;
				this.bestDiscount = bestDiscount;
			}


	}
