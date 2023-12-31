package dat3.car.repository;

import dat3.car.dto.CarRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.service.MemberService;
import dat3.car.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

class CarRepositoryTest
	{
		@Autowired
		CarRepository carRepository;
		@Autowired
				ReservationRepository reservationRepository;
		ReservationService reservationService;
		@Autowired
				MemberRepository memberRepository;
		MemberService memberService;

		boolean isInitialized=false;
		@BeforeEach
		void setUp()
			{
				Member m1;
				Reservation r1,r2;
				LocalDate date=LocalDate.now();
				LocalDate date1=date.plusDays(14);

				if(!isInitialized){
					carRepository.deleteAll();
					Car c1=new Car("Kia","ModelC",472.81,6);
					carRepository.save(c1);
					Car c2=new Car("VW","ModelD",303.78,8);
					carRepository.save(c2);
					Car c3=new Car("Volvo","ModelA",266.65,4);
					carRepository.save(c3);
					Car c4=new Car("Volvo","ModelE",305.31,4);
					carRepository.save(c4);
					Car c5=new Car("Volvo","ModelA",200.15,6);
					carRepository.save(c5);
					m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
					r1=reservationRepository.saveAndFlush(new Reservation(c1,m1,date));
					r2=reservationRepository.saveAndFlush(new Reservation(c2,m1,date1));
					isInitialized=true;

				}
			}
			@Test
		public void testAll(){
			long count=carRepository.count();
				System.out.println(count);
			assertEquals(5,count);
			}

		@Test
		void findAllByBrandAndModel()
			{
				List<Car> cars=carRepository.findAllByBrandAndModel("Volvo","ModelA");
				assertEquals(2,cars.size(),"method should find two cars");
				List<Car> cars1=carRepository.findAllByBrandAndModel("Audi","ModelA");
				assertEquals(0,cars1.size(),"method should find zero cars");

			}

		@Test
		void findAllByReservationsFalse()
			{
				List<Car> cars=carRepository.findAllByReservationsIsNull();
				assertEquals(3,cars.size(), "should return the number of cars with no reservations");
			}
	}