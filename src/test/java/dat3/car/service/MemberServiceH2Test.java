package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

	@Autowired
	MemberRepository memberRepository;
	MemberService memberService;
	@Autowired
	ReservationRepository reservationRepository;
	ReservationService reservationService;
	@Autowired
	CarRepository carRepository;
	CarService carService;



	Member m1, m2; //Talk about references in Java for why we don't add the "isInitialize flag"
	Car car1,car2;
	LocalDate date1=LocalDate.now();
	LocalDate date2=date1.plusDays(1);
	Reservation r1;

	@BeforeEach
	void setUp() {

		m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
		m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
		memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
		car1=carRepository.saveAndFlush(new Car("Ford","Fiesta",270.30,15));
		car2=carRepository.saveAndFlush(new Car("Toyota","Yaris",150,152));
		carService=new CarService(carRepository);
		r1=reservationRepository.saveAndFlush(new Reservation(car1,m1,date1));
		reservationService=new ReservationService(memberRepository,carRepository,reservationRepository);
	}

	@Test
	void testGetMembersAllDetails() {
		List<MemberResponse> response= memberService.getMembers(true);
		assertEquals(2,response.size(),"I should return 2 members");
		LocalDateTime time=response.get(0).getCreated();
		assertNotNull(time,"Date must be set when includeAll=true");
	}

	@Test
	void testGetMembersNoDetails() {
		List<MemberResponse> reponse= memberService.getMembers(false);
		assertEquals(2,reponse.size(),"I should return 2 members");
		LocalDateTime time=reponse.get(0).getCreated();
		assertNull(time,"Date must be null when includeAll=false");
	}

	@Test
	void testFindByIdFound() {
		MemberResponse res= memberService.findById("user1");
		assertEquals("user1",res.getUsername());
		assertEquals("email1",res.getEmail());
	}

	@Test
	void testFindByIdNotFound() {
		//This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
		ResponseStatusException ex= assertThrows(ResponseStatusException.class,
				()->memberService.findById("I dont exist"));
		assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
	}
//1
	@Test
		/* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
		 * Internally addMember savex a Member entity to the database*/
	void testAddMember_UserDoesNotExist() {
		//Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
		MemberRequest request=MemberRequest.builder().
				username("user3").
				password("pw3").
				email("email3").
				firstName("fn3").
				lastName("ln3")
				.build();
		MemberResponse response=memberService.addMember(request);
		assertEquals("user3",response.getUsername(),"user should be created");
		assertTrue(memberRepository.existsById("user3"));
	}

	@Test
	void testAddMember_UserDoesExistThrows() {
		//This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
		MemberRequest request= new MemberRequest(m1);

		ResponseStatusException ex= assertThrows(ResponseStatusException.class,
				()->memberService.addMember(request));
		assertEquals(HttpStatus.BAD_REQUEST,ex.getStatusCode());
	}
// herfra
	@Test
	void testEditMemberWithExistingUsername() {

		MemberRequest memberRequest=new MemberRequest(m1);
		memberRequest.setFirstName("new first name");
		memberRequest.setLastName("new Last Name");
		memberService.editMember(memberRequest,"user1");
		//memberRepository.flush();
		// da min editmember returnerer en ResponseEntity kan jeg tjekke på den:
		assertEquals(ResponseEntity.ok(true),memberService.editMember(memberRequest, m1.getUsername()));
		// hvis min min edit member var void skulle den tjekke i repository:
		MemberResponse res=memberService.findById(m1.getUsername());
		assertEquals("new first name",res.getFirstName());
		assertEquals("new Last Name",res.getLastName());


	}

	@Test
	void testEditMemberNON_ExistingUsernameThrows() {
		//This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
		MemberRequest request=new MemberRequest();


		ResponseStatusException ex= assertThrows(ResponseStatusException.class,
				()->memberService.editMember(request,"I dont exist"));
		assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());

	}

	@Test
	void testEditMemberChangePrimaryKeyThrows() {
		//Create a MemberRequest from an existing member we can edit
		MemberRequest request = new MemberRequest(m1);
		request.setUsername("new UserName");
		ResponseStatusException ex= assertThrows(ResponseStatusException.class,
				()->memberService.editMember(request,"user1"));
		assertEquals(HttpStatus.BAD_REQUEST,ex.getStatusCode());
		assertEquals("Cannot change username",ex.getReason());
	}
// hertil
	@Test
	void testSetRankingForUser() {
		memberService.setRanking(m1.getUsername(),5);
		MemberResponse response=memberService.findById("user1");
		assertEquals(5,response.getRanking());
	}

	@Test
	void testSetRankingForNoExistingUser() {

		ResponseStatusException ex= assertThrows(ResponseStatusException.class,
				()->memberService.setRanking("nonExisting",5));
		assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
	}
	@Test
	void testDeleteMemberByUsername() {
	memberService.deleteMemberByID("user1");
	assertFalse(memberRepository.existsByUsername("user1"));
	}

	@Test
	void testDeleteMember_ThatDontExist() {
		;
		ResponseStatusException ex= assertThrows(ResponseStatusException.class,
				()->memberService.deleteMemberByID("nonexisting"));
		assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
	}
}
