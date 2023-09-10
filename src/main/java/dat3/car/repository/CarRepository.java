package dat3.car.repository;

import dat3.car.dto.CarRequest;
import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer>
	{



		List<Car> findAllByBrandAndModel(String brand, String model);
		List<Car> findAllByReservationsIsNull();
	}
