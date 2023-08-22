package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner
	{
		CarRepository carRepository;
		public DeveloperData(CarRepository carRepository){
			this.carRepository=carRepository;
		}
		@Override
		public void run(ApplicationArguments args) throws Exception
			{
				List< Car> cars=new ArrayList<>();
				cars.add(new Car( "Kia","ModelC",472.81,6));
				cars.add(new Car( "VW","ModelD",303.78,8));
				cars.add(new Car( "Volvo","ModelA",266.65,4));
				cars.add(new Car( "Volvo","ModelE",305.31,4));
				cars.add(new Car( "Kia","ModelE",190.93,9));
				cars.add(new Car( "Kia","ModelA",247.41,8));
				cars.add(new Car( "VW","ModelC",424.1,7));
				cars.add(new Car( "VW","ModelA",325.15,10));
				cars.add(new Car( "VW","ModelB",76.17,15));
				cars.add(new Car( "Volvo","ModelC",97.1,12));
				cars.add(new Car( "VW","ModelE",456.88,6));
				cars.add(new Car( "VW","ModelD",321.33,4));
				cars.add(new Car( "Volvo","ModelD",214.42,10));
				cars.add(new Car( "Volvo","ModelD",280.27,7));
				cars.add(new Car( "Kia","ModelD",471.76,2));
				cars.add(new Car( "Kia","ModelB",208.13,13));
				cars.add(new Car( "VW","ModelB",434.57,5));
				cars.add(new Car( "Volvo","ModelD",125.88,1));
				cars.add(new Car( "VW","ModelE",43.65,10));
				cars.add(new Car( "Kia","ModelB",167.7,3));
				cars.add(new Car( "Kia","ModelD",411.08,7));
				cars.add(new Car( "Volvo","ModelD",384.56,12));
				cars.add(new Car( "Kia","ModelA",470.83,2));
				cars.add(new Car( "Kia","ModelD",48.68,3));
				cars.add(new Car( "VW","ModelE",215.82,10));
				cars.add(new Car( "Ford","ModelA",84.54,8));
				cars.add(new Car( "Volvo","ModelD",398.37,15));
				cars.add(new Car( "VW","ModelB",37.51,1));
				cars.add(new Car( "VW","ModelB",182.37,9));
				cars.add(new Car( "Volvo","ModelE",69.81,14));
				cars.add(new Car( "VW","ModelD",231.23,7));
				cars.add(new Car( "VW","ModelB",308.14,10));
				cars.add(new Car( "VW","ModelA",29.91,2));
				cars.add(new Car( "VW","ModelA",167.01,12));
				cars.add(new Car( "Ford","ModelE",470.14,4));
				cars.add(new Car( "Ford","ModelE",473.45,12));
				cars.add(new Car( "Ford","ModelA",217.61,1));
				cars.add(new Car( "VW","ModelE",329.21,4));
				cars.add(new Car( "VW","ModelC",179.2,15));
				cars.add(new Car( "VW","ModelC",8.42,10));
				cars.add(new Car( "Kia","ModelB",494.88,3));
				cars.add(new Car( "Kia","ModelC",242.72,2));
				cars.add(new Car( "Volvo","ModelD",332.3,13));
				cars.add(new Car( "Volvo","ModelD",377.74,1));
				cars.add(new Car( "Kia","ModelB",274.17,7));
				cars.add(new Car( "VW","ModelC",203.02,11));
				cars.add(new Car( "Ford","ModelE",361.64,10));
				cars.add(new Car( "VW","ModelE",435.95,5));
				cars.add(new Car( "Volvo","ModelB",266.65,4));
				cars.add(new Car( "Volvo","ModelB",142.39,14));
				carRepository.saveAll(cars);
			}
	}
