package car.com.Cars.repository;

import car.com.Cars.model.entity.CarDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CarDetailsRepository extends JpaRepository<CarDetailsEntity, Integer> {

    /**
     * removes the cars with specified id and return Object
     *
     * @param id of the car
     * @return List<CarDetailsEntity> which are deleted from database
     */
    @Transactional
    List<CarDetailsEntity> removeById(Integer id);
}
