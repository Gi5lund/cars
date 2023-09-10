package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.service.CarService;
import dat3.car.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

class MemberRepositoryTest
	{
		@Autowired
		MemberRepository memberRepository;
		@Autowired
		ReservationRepository reservationRepository;
		ReservationService reservationService;
		@Autowired
		CarRepository carRepository;
		CarService carService;
		boolean isInitialized=false;
		@BeforeEach
		void setUp()
			{
				Member m1,m2;
				Reservation r1,r2;
				LocalDate date=LocalDate.now();
				LocalDate date1=date.plusDays(14);

				if(!isInitialized){
					memberRepository.deleteAll();
					m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
					m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
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

					r1=reservationRepository.saveAndFlush(new Reservation(c1,m1,date));
					r2=reservationRepository.saveAndFlush(new Reservation(c2,m1,date1));
					isInitialized=true;

				}
			}

		@Test
		void findAllByReservationsIsNotNull()
			{
				List<Member> members=memberRepository.findAllByReservationsIsNotNull();
				assertEquals(1,members.size(),"should be 1 member with reservertions");
				assertEquals("fn1",members.get(0).getFirstName());
			}
	}