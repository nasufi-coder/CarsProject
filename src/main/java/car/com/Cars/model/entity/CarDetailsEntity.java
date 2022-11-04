package car.com.Cars.model.entity;

import car.com.Cars.model.enums.TireTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entity of car
 */
@Document
@Data
public class CarDetailsEntity {

    @Id
    private String id;

    private String carName;

    private Float tireSize;

    private TireTypeEnum tireType;


}
