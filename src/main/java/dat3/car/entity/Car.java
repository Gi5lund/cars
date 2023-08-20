package dat3.car.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car", schema = "cars")

public class Car
	{

		@Id
		@GeneratedValue
		private Long ID;
		@Column(name = "car_brand")
		private String brand;
		@Column (name = "car_model")
		private String model;
		@Column(name = "rental_price_day")
		private double pricePrDay;
		@Column(name = "max_discount")
		private int bestDiscount;

		public Car(Long ID, String brand, String model, double pricePrDay, int bestDiscount)
			{
				this.ID = ID;
				this.brand = brand;
				this.model = model;
				this.pricePrDay = pricePrDay;
				this.bestDiscount = bestDiscount;
			}

		public void setID(Long id)
			{
				this.ID = id;
			}

		public Long getID()
			{
				return ID;
			}

		public String getBrand()
			{
				return brand;
			}

		public void setBrand(String brand)
			{
				this.brand = brand;
			}

		public String getModel()
			{
				return model;
			}

		public void setModel(String model)
			{
				this.model = model;
			}

		public double getPricePrDay()
			{
				return pricePrDay;
			}

		public void setPricePrDay(double pricePrDay)
			{
				this.pricePrDay = pricePrDay;
			}

		public int getBestDiscount()
			{
				return bestDiscount;
			}

		public void setBestDiscount(int bestDiscount)
			{
				this.bestDiscount = bestDiscount;
			}
	}
