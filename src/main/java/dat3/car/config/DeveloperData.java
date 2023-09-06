package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner
	{
		CarRepository carRepository;
		MemberRepository memberRepository;
		ReservationRepository reservationRepository;

		public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository)
			{
				this.carRepository = carRepository;
				this.memberRepository = memberRepository;
				this.reservationRepository = reservationRepository;
			}

		//Obviously this data setup must never be used in production
		private final String passwordUsedByAll = "test12";

		@Override
		public void run(ApplicationArguments args) throws Exception {
			List<Member> members = MemberTestDataFactory.generateTestMembers("test12");
			memberRepository.saveAll(members);
			List<Car> cars = CarTestDataFactory.generateTestCars();
			carRepository.saveAll(cars);
			Car car1 = new Car("VW", "Golf", 760, 25);
			Member m1 = new Member("Jan","test12","a@b.dk","Jan","Jensen","Lyngbyvej 1","Lyngby","2800");
			carRepository.save(car1);
			memberRepository.save(m1);

			LocalDate date1=LocalDate.now().plusDays(2);
			LocalDate date2=LocalDate.now().plusDays(3);
			Reservation r1=new Reservation(car1,m1,date1);
			Reservation r2=new Reservation(car1,m1,date2);
			reservationRepository.save(r1);
			reservationRepository.save(r2);
		}




	}
