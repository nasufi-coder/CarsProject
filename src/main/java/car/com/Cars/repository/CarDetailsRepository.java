package car.com.Cars.repository;

import car.com.Cars.model.entity.CarDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CarDetailsRepository extends MongoRepository<CarDetailsEntity, String> {

    /**
     * removes the cars with specified id and return a list of Objects
     *
     * @param id of the car
     * @return List<CarDetailsEntity> which are deleted from database
     */
    @Transactional
    List<CarDetailsEntity> removeById(String id);
}
