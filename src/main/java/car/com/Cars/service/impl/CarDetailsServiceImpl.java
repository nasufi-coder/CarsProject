package car.com.Cars.service.impl;

import car.com.Cars.exception.CustomExeption.CarNotFoundException;
import car.com.Cars.exception.CustomExeption.DeleteException;
import car.com.Cars.mapper.CarDetailsMapper;
import car.com.Cars.model.business.Car;
import car.com.Cars.model.entity.CarDetailsEntity;
import car.com.Cars.model.rabbit.QueueCar;
import car.com.Cars.repository.CarDetailsRepository;
import car.com.Cars.service.CarDetailsService;
import car.com.Cars.service.rabbit.RabbitMQSender;
import com.baeldung.openapi.model.CarDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a class which implements all CarDetailsServiceMethods
 */
@Service
@AllArgsConstructor
public class CarDetailsServiceImpl implements CarDetailsService {
    private final CarDetailsRepository carDetailsRepository;
    private final CarDetailsMapper carDetailsMapper;

    private final RabbitMQSender rabbitMQSender;

    @Override
    public CarDTO findById(Integer id) {
        var carFound = carDetailsRepository.findById(id);
        if (carFound.isEmpty()) {
            throw new CarNotFoundException("Car requested could not be found!");
        }
        var car = carDetailsMapper.convertEntityToBusiness(carFound.get());
        return carDetailsMapper.convertBusinessToDto(car);
    }

    @Override
    public List<CarDTO> findAllCars() {
        List<CarDetailsEntity> carDetailEntities = carDetailsRepository.findAll();
        if (carDetailEntities.isEmpty()) {
            throw new CarNotFoundException("There are no results!");
        }
        var cars = carDetailEntities.stream().map(carDetailsMapper::convertEntityToBusiness).collect(Collectors.toList());
        return cars.stream().map(carDetailsMapper::convertBusinessToDto).collect(Collectors.toList());

    }

    @Override
    public CarDTO updateCar(CarDTO carDTO) {
        if (carDTO.getId() == null || findById(carDTO.getId()) == null) {
            throw new CarNotFoundException("Car you have requested does not exists!");
        }
        return saveCar(carDTO);
    }

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        var car = carDetailsMapper.convertDtoToBusiness(carDTO);
        var carDetailsEntity = carDetailsMapper.convertBusinessToEntity(car);
        var createdCar = carDetailsRepository.save(carDetailsEntity);
        var carCreated = carDetailsMapper.convertEntityToBusiness(createdCar);
        sendMessage(carCreated);
        return carDetailsMapper.convertBusinessToDto(carCreated);
    }

    @Override
    public CarDTO deleteCar(Integer id) {
        var response = carDetailsRepository.removeById(id);
        if (response.isEmpty()) {
            throw new CarNotFoundException("No records found to be deleted!");
        } else if (response.size() > 1) {
            throw new DeleteException("No records found to be deleted!");
        }
        var carDeleted = carDetailsMapper.convertEntityToBusiness(response.get(0));
        sendMessage(carDeleted);
        return carDetailsMapper.convertBusinessToDto(carDeleted);
    }

    private void sendMessage(Car car) {
        var message = QueueCar.builder()
                .carName(car.getCarName())
                .tireSize(car.getTireSize())
                .build();

        rabbitMQSender.send(message);
    }
}
