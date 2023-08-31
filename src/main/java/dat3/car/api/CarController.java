package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;

import dat3.car.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")

public class CarController
	{
		CarService carService;
		public CarController(CarService carService){
			this.carService=carService;
		}
		//security Admin only
		@GetMapping
		List<CarResponse> getCar(){
			return carService.getCars(false);
		}
		//Security Admin
		@GetMapping(path = "/{ID}")
		CarResponse getCarById(@PathVariable String ID) throws Exception {
			return CarService.findById(ID);
		}

		//Security --> Anonymous
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		CarResponse addCar(@RequestBody CarRequest body){
			return carService.addCar(body);
		}

		//Security ???
		@PutMapping("/{username}")
		ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
			return carService.editCar(body,id);
		}

		//Security ????
		@PatchMapping("/ranking/{username}/{value}")
		ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {
			return memberService.setRanking(username,value);
		}

		// Security ????
		@DeleteMapping("/{username}")
		ResponseEntity<Boolean> deleteMemberByUsername(@PathVariable String username) {
			return memberService.deleteMemberByID(username);
		}

	}

