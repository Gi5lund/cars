package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class CarService
	{
		CarRepository carRepository;

		public CarService(CarRepository carRepository)
			{
				this.carRepository = carRepository;
			}


		 public CarResponse findById(Integer id)
			{
				Car car=getCar(id);
				return new CarResponse(car,true);
			}

		public List<CarResponse> getCars(boolean includeAll)
			{
				List<Car> cars=carRepository.findAll();
				List<CarResponse> respons=cars.stream().map((car -> new CarResponse(car,includeAll))).toList();
				return respons;
			}

		public CarResponse addCar(CarRequest body)
			{
				Car newCar=CarRequest.getCarEntity(body);
				if(carRepository.existsById(body.getId())){
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This Car already exists");
				}
				newCar=carRepository.save(newCar);
				return new CarResponse(newCar,true);
			}

		public ResponseEntity<Boolean> editCar(CarRequest body, int id)
			{
				Car car=getCar(id);
				if(body.getId()!=id){
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change car-Id");
				}
				car.setBrand(body.getBrand());
				car.setModel(body.getModel());
				car.setBestDiscount(body.getBestDiscount());
				car.setPricePrDay(body.getPricePrDay());
				carRepository.save(car);
				return ResponseEntity.ok(true);
			}
		private Car getCar(int id){
			Car car;
			car=carRepository.findById(id).
					orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this Id does not exist"));
			return car;
		}
	}
