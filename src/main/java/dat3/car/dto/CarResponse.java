package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for @Builder
@Builder  //I will demo it's purpose in the class
//It's really IMPORTANT that you understand the purpose of this annotation
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CarResponse
	{
		String brand;
		String model;
		Double pricePrDay;
		Integer bestDiscount;
		LocalDateTime created;

		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
		LocalDateTime edited;

		public CarResponse(Car c,boolean includeAll)
			{
				this.brand = c.getBrand();
				this.model = c.getModel();
				this.pricePrDay = c.getPricePrDay();
				if (includeAll){
					this.bestDiscount = c.getBestDiscount();
					this.edited=c.getEdited();
					this.created=c.getCreated();
				}

			}
	}
