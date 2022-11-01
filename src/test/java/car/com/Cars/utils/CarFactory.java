package car.com.Cars.utils;

import car.com.Cars.model.entity.CarDetailsEntity;
import car.com.Cars.model.enums.TireTypeEnum;
import com.baeldung.openapi.model.CarDTO;

import java.math.BigDecimal;

public class CarFactory {

    public static CarDetailsEntity createCarEntity(){
        var car = new CarDetailsEntity();
        car.setCarName("VW");
        car.setTireSize(1.2F);
        car.setTireType(TireTypeEnum.LARGE);
        return car;
    }

    public static CarDTO createCarDTO(){
        var car = new CarDTO();
        car.setCarName("VW");
        car.setTireSize(new BigDecimal("1.2"));
        car.setTireType(CarDTO.TireTypeEnum.LARGE);
        return car;
    }
}
