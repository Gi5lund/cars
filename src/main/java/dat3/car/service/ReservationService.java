package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService
	{
		MemberRepository memberRepository;
		CarRepository carRepository;

		static ReservationRepository reservationRepository;

		public ReservationService(MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository)
			{
				this.memberRepository = memberRepository;
				this.carRepository = carRepository;
				this.reservationRepository = reservationRepository;
			}
		public ReservationResponse reserveCar(ReservationRequest body){
			if(body.getDate().isBefore(LocalDate.now())){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date in past not allowed");
			}
			Member member = memberRepository.findById(body.getUserName()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this id found"));
			Car car = carRepository.findById(body.getCarId()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Car with this id found"));
			//What if already reserved
				if(reservationRepository.existsByCarIdAndRentalDate(body.getCarId(),body.getDate())){
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This car is already booked on this date");
				}
			Reservation res = reservationRepository.save(new Reservation(car,member,body.getDate()));
			return  new ReservationResponse(res);
		}
		public static  List<ReservationResponse> getUserReservations(Member member){
			List<Reservation> reservations=reservationRepository.findAllByMember_Username(member.getUsername());
			List<ReservationResponse> responseList=reservations.stream().map((reservation -> new ReservationResponse(reservation))).toList();
			return responseList;
		}

	}
