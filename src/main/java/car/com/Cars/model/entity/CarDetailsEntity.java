package car.com.Cars.model.entity;

import car.com.Cars.model.enums.TireTypeEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of the car
 */
@Entity
@Table(name = "carDetails")
@Data
public class CarDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "car_name", nullable = false)
    private String carName;

    @Column(name = "tire_size", nullable = false)
    private Float tireSize;

    @Column(name = "tire_type", nullable = false)
    private TireTypeEnum tireType;


}
