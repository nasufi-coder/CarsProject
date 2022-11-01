package car.com.Cars.controller;

import car.com.Cars.service.CarDetailsService;
import com.baeldung.openapi.api.CarsApi;
import com.baeldung.openapi.model.CarDTO;
import com.baeldung.openapi.model.CarsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarDetailsController implements CarsApi {

    private final CarDetailsService carDetailsService;

    @Override
    public ResponseEntity<CarDTO> getCar(Integer id) {
        var car = carDetailsService.findById(id);
        return ResponseEntity.ok(car);
    }

    @Override
    public ResponseEntity<CarsDTO> listCars() {
        var cars = carDetailsService.findAllCars();
        var response = new CarsDTO().cars(cars);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CarDTO> createCar(CarDTO carDTO) {
        var response = carDetailsService.saveCar(carDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CarDTO> updateCar(CarDTO carDTO) {
        var response = carDetailsService.updateCar(carDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CarDTO> deleteCar(Integer id) {
        var response = carDetailsService.deleteCar(id);
        return ResponseEntity.status(202).build();
    }
}
