package car.com.Cars.service;

import com.baeldung.openapi.model.CarDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarDetailsService {

    /**
     * Find car by id
     *
     * @param id of the car
     * @return CarDTO
     */
    CarDTO findById(String id);

    /**
     * Find all cars
     *
     * @return List<CarDTO>
     */
    List<CarDTO> findAllCars();

    /**
     * save car in database
     *
     * @param carDTO filled with car details
     * @return CarDTO that is saved in database
     */
    CarDTO saveCar(CarDTO carDTO);

    /**
     * update specific car
     *
     * @param carDTO filled with car details
     * @return CarDTO that is updated in database
     */
    CarDTO updateCar(CarDTO carDTO);

    /**
     * delete specific car
     *
     * @param id from the car
     * @return CarDTO that is deleted from database
     */
    CarDTO deleteCar(String id);
}
