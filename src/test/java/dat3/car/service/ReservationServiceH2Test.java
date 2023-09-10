package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.HttpStatusHandler;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ReservationServiceH2Test
	{
@Autowired
		MemberRepository memberRepository;
MemberService memberService;
@Autowired
		CarRepository carRepository;
CarService carService;
		@Autowired
		ReservationRepository reservationRepository;
ReservationService reservationService;
		Member m1, m2;
		Car car1, car2;
		Reservation r1,r2,r3,r4;
		LocalDate date1=LocalDate.now();
		LocalDate date2=date1.plusDays(1);
		LocalDate date3=date1.minusDays(5);
		@BeforeEach
		void setUp() {
			m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
			m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
			memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
			car1=carRepository.saveAndFlush(new Car("Ford","Fiesta",270.30,15));
			car2=carRepository.saveAndFlush(new Car("Toyota","Yaris",150,152));
			carService=new CarService(carRepository);
			r1=reservationRepository.saveAndFlush(new Reservation(car1,m1,date1));
		//	r2=reservationRepository.saveAndFlush(new Reservation(car1,m2,date1));
		//	r3=reservationRepository.saveAndFlush(new Reservation(car2,m1,date3));
			reservationService=new ReservationService(memberRepository,carRepository,reservationRepository);
		}
		@Test
		void reserveCarDateNotInFuture()
			{
				ReservationRequest request=new ReservationRequest(car1.getId(), m1.getUsername(), date3);
				//ReservationResponse response=reservationService.reserveCar(request);
				ResponseStatusException ex= assertThrows(ResponseStatusException.class,
						()->reservationService.reserveCar(request));
				assertEquals(HttpStatus.BAD_REQUEST,ex.getStatusCode());
			}
		@Test
		void reserveCarNotFound()
			{
				ReservationRequest request=new ReservationRequest(-1, m1.getUsername(), date2);
				ResponseStatusException ex= assertThrows(ResponseStatusException.class,
						()->reservationService.reserveCar(request));
				assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
			}

		@Test
		void reserveCarMemberNotFound()
			{
				ReservationRequest request=new ReservationRequest(car2.getId(), "Usr23", date2);
				ResponseStatusException ex= assertThrows(ResponseStatusException.class,
						()->reservationService.reserveCar(request));
				assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
			}
		@Test
		void reserveCarBooked()
			{
				ReservationRequest request=new ReservationRequest(car1.getId(), m2.getUsername(), date1);
				ResponseStatusException ex= assertThrows(ResponseStatusException.class,
						()->reservationService.reserveCar(request));
				assertEquals(HttpStatus.BAD_REQUEST,ex.getStatusCode());
			}
	/*	@Test
		void reserveCarSucces()
			{
				ReservationRequest request=new ReservationRequest(car2.getId(),m2.getUsername(),date2);
				ReservationResponse res=reservationService.reserveCar(request);
				assertEquals("Toyota",res.getBrand());




			}*/
	}