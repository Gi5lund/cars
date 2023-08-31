package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner
	{
		CarRepository carRepository;
		MemberRepository memberRepository;
		public DeveloperData(CarRepository carRepository,MemberRepository memberRepository){
			this.carRepository=carRepository;
			this.memberRepository=memberRepository;
		}
		@Override
		public void run(ApplicationArguments args) throws Exception
			{
				List< Car> cars=new ArrayList<>();
				cars.add(new Car(request.getId(), "Kia","ModelC",472.81,6));
				cars.add(new Car(request.getId(), "VW","ModelD",303.78,8));
				cars.add(new Car(request.getId(), "Volvo","ModelA",266.65,4));
				cars.add(new Car(request.getId(), "Volvo","ModelE",305.31,4));
				cars.add(new Car(request.getId(), "Kia","ModelE",190.93,9));
				cars.add(new Car(request.getId(), "Kia","ModelA",247.41,8));
				cars.add(new Car(request.getId(), "VW","ModelC",424.1,7));
				cars.add(new Car(request.getId(), "VW","ModelA",325.15,10));
				cars.add(new Car(request.getId(), "VW","ModelB",76.17,15));
				cars.add(new Car(request.getId(), "Volvo","ModelC",97.1,12));
				cars.add(new Car(request.getId(), "VW","ModelE",456.88,6));
				cars.add(new Car(request.getId(), "VW","ModelD",321.33,4));
				cars.add(new Car(request.getId(), "Volvo","ModelD",214.42,10));
				cars.add(new Car(request.getId(), "Volvo","ModelD",280.27,7));
				cars.add(new Car(request.getId(), "Kia","ModelD",471.76,2));
				cars.add(new Car(request.getId(), "Kia","ModelB",208.13,13));
				cars.add(new Car(request.getId(), "VW","ModelB",434.57,5));
				cars.add(new Car(request.getId(), "Volvo","ModelD",125.88,1));
				cars.add(new Car(request.getId(), "VW","ModelE",43.65,10));
				cars.add(new Car(request.getId(), "Kia","ModelB",167.7,3));
				cars.add(new Car(request.getId(), "Kia","ModelD",411.08,7));
				cars.add(new Car(request.getId(), "Volvo","ModelD",384.56,12));
				cars.add(new Car(request.getId(), "Kia","ModelA",470.83,2));
				cars.add(new Car(request.getId(), "Kia","ModelD",48.68,3));
				cars.add(new Car(request.getId(), "VW","ModelE",215.82,10));
				cars.add(new Car(request.getId(), "Ford","ModelA",84.54,8));
				cars.add(new Car(request.getId(), "Volvo","ModelD",398.37,15));
				cars.add(new Car(request.getId(), "VW","ModelB",37.51,1));
				cars.add(new Car(request.getId(), "VW","ModelB",182.37,9));
				cars.add(new Car(request.getId(), "Volvo","ModelE",69.81,14));
				cars.add(new Car(request.getId(), "VW","ModelD",231.23,7));
				cars.add(new Car(request.getId(), "VW","ModelB",308.14,10));
				cars.add(new Car(request.getId(), "VW","ModelA",29.91,2));
				cars.add(new Car(request.getId(), "VW","ModelA",167.01,12));
				cars.add(new Car(request.getId(), "Ford","ModelE",470.14,4));
				cars.add(new Car(request.getId(), "Ford","ModelE",473.45,12));
				cars.add(new Car(request.getId(), "Ford","ModelA",217.61,1));
				cars.add(new Car(request.getId(), "VW","ModelE",329.21,4));
				cars.add(new Car(request.getId(), "VW","ModelC",179.2,15));
				cars.add(new Car(request.getId(), "VW","ModelC",8.42,10));
				cars.add(new Car(request.getId(), "Kia","ModelB",494.88,3));
				cars.add(new Car(request.getId(), "Kia","ModelC",242.72,2));
				cars.add(new Car(request.getId(), "Volvo","ModelD",332.3,13));
				cars.add(new Car(request.getId(), "Volvo","ModelD",377.74,1));
				cars.add(new Car(request.getId(), "Kia","ModelB",274.17,7));
				cars.add(new Car(request.getId(), "VW","ModelC",203.02,11));
				cars.add(new Car(request.getId(), "Ford","ModelE",361.64,10));
				cars.add(new Car(request.getId(), "VW","ModelE",435.95,5));
				cars.add(new Car(request.getId(), "Volvo","ModelB",266.65,4));
				cars.add(new Car(request.getId(), "Volvo","ModelB",142.39,14));
				carRepository.saveAll(cars);

				List<Member> members=new ArrayList<>();
				members.add( new Member("user1", "password1", "user1@example.com", "John", "Doe", "123 Main St", "New York", "10001"));
				members.add( new Member("user2", "password2", "user2@example.com", "Jane", "Smith", "456 Elm St", "Los Angeles", "90001"));
				members.add( new Member("user3", "password3", "user3@example.com", "Michael", "Johnson", "789 Oak St", "Chicago", "60601"));
				members.add( new Member("user4", "password4", "user4@example.com", "Emily", "Williams", "321 Pine St", "Houston", "77001"));
				members.add( new Member("user5", "password5", "user5@example.com", "David", "Brown", "654 Birch St", "Miami", "33101"));
				memberRepository.saveAll(members);
			}
	}
