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
@CrossOrigin
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
		@GetMapping("/admin")
		List<CarResponse> getCars(){
			return carService.getCars(true);
		}
		//Security Admin
		@GetMapping(path = "/{id}")
		CarResponse getCarById(@PathVariable Integer id) throws Exception {
			return carService.findById(id);
		}

		//Security --> Anonymous
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		CarResponse addCar(@RequestBody CarRequest body){
			return carService.addCar(body);
		}

		//Security ???
		@PutMapping("/{id}")
		CarResponse editCar(@RequestBody CarRequest body, @PathVariable int id){
			return carService.editCar(body,id);
		}

		//Security ????
//		@PatchMapping("/ranking/{username}/{value}")
//		ResponseEntity<Boolean> setRankingForUser(@PathVariable Integer Id, @PathVariable int value) {
//
//			return carService.set();
//		}
//
//		// Security ????
//		@DeleteMapping("/{username}")
//		ResponseEntity<Boolean> deleteMemberByUsername(@PathVariable String username) {
//			return carService.deleteMemberByID(username);
//		}

	}

