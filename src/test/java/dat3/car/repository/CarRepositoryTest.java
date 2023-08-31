package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

class CarRepositoryTest
	{
		@Autowired
		CarRepository carRepository;

		boolean isInitialized=false;
		@BeforeEach
		void setUp()
			{
				if(!isInitialized){
					carRepository.deleteAll();
					carRepository.save(new Car(request.getId(), "Kia","ModelC",472.81,6));
					carRepository.save(new Car(request.getId(), "VW","ModelD",303.78,8));
					carRepository.save(new Car(request.getId(), "Volvo","ModelA",266.65,4));
					carRepository.save(new Car(request.getId(), "Volvo","ModelE",305.31,4));
					isInitialized=true;
				}
			}
			@Test
		public void testAll(){
			long count=carRepository.count();
				System.out.println(count);
			assertEquals(4,count);
			}
	}