package car.com.Cars.controller.BasicModels;

import car.com.Cars.repository.CarDetailsRepository;
import car.com.Cars.utils.CarFactory;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@JGivenStage
public class GivenBasicModel extends Stage<GivenBasicModel> {

    @Autowired
    private CarDetailsRepository repository;

    Integer lastIdInserted;

    /**
     * Method to clean the table! Before each other method.
     */
    @BeforeTestMethod
    void cleanUp(){
        repository.deleteAll();
    }

    /**
     * Method to create a car in database!
     *
     * @return self
     */
    public GivenBasicModel car_exist_in_db() {
        var car = CarFactory.createCarEntity();
        var carInserted=  repository.save(car);
        lastIdInserted = carInserted.getId();
        return self();
    }

    /**
     * Empty method to stimulate an empty table!
     *
     * @return self
     */
    public GivenBasicModel no_car_exist_in_db() {
        return self();
    }

    public Integer getLastIdInserted() {
        return lastIdInserted;
    }
}