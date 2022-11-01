package car.com.Cars.controller.BasicModels;

import car.com.Cars.repository.CarDetailsRepository;
import car.com.Cars.utils.CarFactory;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class GivenBasicModel extends Stage<GivenBasicModel> {

    @Autowired
    private CarDetailsRepository repository;

    /**
     * Method to clean the table! Before each other method.
     */
    @BeforeEach
    void cleanUp(){
        repository.deleteAll();
    }

    /**
     * Method to create a car in database!
     *
     * @return
     */
    public GivenBasicModel car_exist_in_db() {
        var car = CarFactory.createCarEntity();
        var carInserted=  repository.save(car);
        return self();
    }

    /**
     * Empty method to stimulate an empty table!
     *
     * @return
     */
    public GivenBasicModel no_car_exist_in_db() {
        return self();
    }
}