package car.com.Cars.model.business;

import car.com.Cars.model.enums.TireTypeEnum;
import lombok.Data;

/**
 * model of car for business layer
 */
@Data
public class Car {

    private Integer id;
    private String carName;
    private Float tireSize;
    private TireTypeEnum tireType;

}
