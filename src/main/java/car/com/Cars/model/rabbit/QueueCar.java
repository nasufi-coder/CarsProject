package car.com.Cars.model.rabbit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueueCar {
    private String carName;
    private Float tireSize;
}
