package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin

@RequestMapping("api/reservations")
public class ReservationController {
	ReservationService service;

	public ReservationController(ReservationService service) {
		this.service = service;
	}

	@PostMapping
	ReservationResponse makeReservation(@RequestBody ReservationRequest res){
		ReservationResponse r = service.reserveCar(res);
		return r;
	}
	@PostMapping("/v2")
	ReservationResponse makeReservationV2(@RequestBody ReservationRequest res, Principal principal){
	//ReservationResponse makeReservationV2(@PathVariable int id,@PathVariable String date Principal principal){
		//res.setUserName("");
		res.setUsername(principal.getName());
		ReservationResponse r = service.reserveCar(res);
		return r;
	}
	@GetMapping(path = "/{userName}")
	public List<ReservationResponse> getReservationsForUser(@PathVariable String userName){
		List<ReservationResponse> res= service.getReservationsForUser(userName);
		return res;
	}
	@GetMapping("/reservations-for-authenticated")
	public List<ReservationResponse> getReservationsForAuthenticatedUser(Principal principal){
		List<ReservationResponse> res = service.getReservationsForUser(principal.getName());
		return res;
	}

	}

