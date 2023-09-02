package dat3.car.dto;


import dat3.car.entity.Car;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequest
	{

		private Integer id;

		private String brand;
		private String model;
		private Double pricePrDay;
		private Integer bestDiscount;



		public CarRequest(Car c)
			{
				this.brand = c.getBrand();
				this.model = c.getModel();
				this.pricePrDay = c.getPricePrDay();
				this.id=c.getId();
				this.bestDiscount=c.getBestDiscount();
			}
		public static Car getCarEntity(CarRequest request){
			return new Car(request.brand,request.model,request.getPricePrDay(),request.getBestDiscount(),request.getId());
		}
	}
