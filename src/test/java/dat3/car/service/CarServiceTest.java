package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;

import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarServiceTest
	{
		@Autowired
		CarRepository carRepository;
		CarService carService;
		Car car1, car2;
		@BeforeEach
		void setup(){
			car1=carRepository.save(new Car("Ford","Fiesta",270.30,15));
			car1=carRepository.save(new Car("Toyota","Yaris",150,15));
			carService=new CarService(carRepository);
		}

		@Test
		void findById()
			{
				CarResponse res=carService.findById(1);
				assertEquals("Ford",res.getBrand(),"should return Brand=Ford");
				assertEquals("Fiesta",res.getModel(),"should return model= Fiesta");
				assertEquals(270.30,res.getPricePrDay());
			}

		@Test
		void getCars()
			{
				List<CarResponse> reponse= carService.getCars(true);
				assertEquals(2,reponse.size(),"I should return 2 members");
				LocalDateTime time=reponse.get(0).getCreated();
				assertNotNull(time,"Date must be set when includeAll=true");
			}

		@Test
		void addCar()
			{
				CarRequest request=CarRequest.builder().
						brand("Kia").
						model("Picante").
						bestDiscount(10).
						pricePrDay(180.22).
						build();
				CarResponse response=carService.addCar(request);
				assertEquals("Kia",response.getBrand());
				assertTrue(carRepository.count()==3);
			}

//		@Test
//		void editCar()
//			{
//			}
	}