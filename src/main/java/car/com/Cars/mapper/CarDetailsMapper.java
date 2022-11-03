package car.com.Cars.mapper;

import car.com.Cars.model.business.Car;
import car.com.Cars.model.entity.CarDetailsEntity;
import com.baeldung.openapi.model.CarDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CarDetailsMapper {

    /**
     * convert entity to Business
     *
     * @param carDetailsEntity from entity layer
     * @return Car
     */
    public Car convertEntityToBusiness(CarDetailsEntity carDetailsEntity);

    /**
     * convert dto to Business
     *
     * @param carDTO from dto layer
     * @return Car
     */
    public Car convertDtoToBusiness(CarDTO carDTO);

    /**
     * convert Business to Entity
     *
     * @param car from business layer
     * @return CarDetailsEntity
     */
    public CarDetailsEntity convertBusinessToEntity(Car car);

    /**
     * convert Business to Dto
     *
     * @param car from business layer
     * @return CarDto
     */
    public CarDTO convertBusinessToDto(Car car);


}
