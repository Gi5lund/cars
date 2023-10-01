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

				newCar=carRepository.save(newCar);
				return new CarResponse(newCar,true);
			}

		public CarResponse editCar(CarRequest body, int id)
			{
				Car car=getCar(id);
				
				car.setBrand(body.getBrand());
				car.setModel(body.getModel());
				car.setBestDiscount(body.getBestDiscount());
				car.setPricePrDay(body.getPricePrDay());
				Car saved=carRepository.save(car);
				return new CarResponse(saved,true);
			}
			//Find all cars with a certain brand and model
			public List<CarResponse> findCarsByBrandAndModel(String brand,String model){
			List<Car> cars=carRepository.findAllByBrandAndModel(brand,model);
				List<CarResponse> respons=cars.stream().map((car -> new CarResponse(car,false))).toList();
				return respons;
			}
		public List<CarResponse> findCarsByBrandAndModel(CarRequest body){
			List<Car> cars=carRepository.findAllByBrandAndModel(body.getBrand(), body.getModel());
			List<CarResponse> respons=cars.stream().map((car -> new CarResponse(car,false))).toList();
			return respons;
		}
		//Find all cars that have not been reserved
		public List<CarResponse> getCarsWithNoReservations(){
			List<Car> cars=carRepository.findAllByReservationsIsNull();
			List<CarResponse> response=cars.stream().map((car -> new CarResponse(car,false))).toList();
			return response;
		}
		private Car getCar(int id){
			Car car;
			car=carRepository.findById(id).
					orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this Id does not exist"));
			return car;
		}
	}
