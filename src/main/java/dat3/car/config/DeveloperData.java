package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

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
			List<Member> members = MemberTestDataFactory.generateTestMembers("test12",5);
			memberRepository.saveAll(members);
			List<Car> cars = CarTestDataFactory.generateTestCars();
			carRepository.saveAll(cars);
			Car car1 = new Car("VW", "Golf", 760, 25);
			Member m1 = new Member("Jan","test12","a@b.dk","Jan","Jensen","Lyngbyvej 1","Lyngby","2800");
			carRepository.save(car1);
			memberRepository.save(m1);

			LocalDate date1=LocalDate.of(2023,12,8);
			LocalDate date2=date1.plusDays(1);
			Reservation r1=new Reservation(car1,m1,date1);
			Reservation r2=new Reservation(car1,m1,date2);
			reservationRepository.save(r1);
			reservationRepository.save(r2);
			System.out.println("xxxx--------->"+car1.getReservations().size());
			System.out.println("xxxx--------->"+m1.getReservations().size());
			System.out.println("should find: "+ reservationRepository.existsByCar_IdAndRentalDate(car1.getId(),date1));
			System.out.println("should find: "+ reservationRepository.existsByCarIdAndRentalDate(car1.getId(),date1));
			setupUserWithRoleUsers();
		}
			@Autowired
	UserWithRolesRepository userWithRolesRepository;

		final String passwordUsedByAll1 = "test12";

		/*****************************************************************************************
		 NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
		 iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
		 *****************************************************************************************/
		private void setupUserWithRoleUsers() {

			System.out.println("******************************************************************************");
			System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
			System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
			System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
			System.out.println("******************************************************************************");
			dat3.security.entity.UserWithRoles user1 = new dat3.security.entity.UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
			dat3.security.entity.UserWithRoles user2 = new dat3.security.entity.UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
			dat3.security.entity.UserWithRoles user3 = new dat3.security.entity.UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
			dat3.security.entity.UserWithRoles user4 = new dat3.security.entity.UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
			user1.addRole(dat3.security.entity.Role.USER);
			user1.addRole(dat3.security.entity.Role.ADMIN);
			user2.addRole(dat3.security.entity.Role.USER);
			user3.addRole(dat3.security.entity.Role.ADMIN);
			//No Role assigned to user4
			userWithRolesRepository.save(user1);
			userWithRolesRepository.save(user2);
			userWithRolesRepository.save(user3);
			userWithRolesRepository.save(user4);
		}





	}
