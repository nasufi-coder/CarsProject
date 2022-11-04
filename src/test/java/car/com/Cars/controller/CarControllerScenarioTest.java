package car.com.Cars.controller;

import car.com.Cars.config.JGivenConfig;
import car.com.Cars.controller.BasicModels.GivenBasicModel;
import car.com.Cars.controller.BasicModels.ThenBasicModel;
import car.com.Cars.controller.BasicModels.WhenBasicModel;
import car.com.Cars.utils.CarFactory;
import com.tngtech.jgiven.integration.spring.junit5.SpringScenarioTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@AutoConfigureMockMvc
@Import(JGivenConfig.class)
@SpringBootTest
public class CarControllerScenarioTest extends SpringScenarioTest<GivenBasicModel, WhenBasicModel, ThenBasicModel> {

    /**
     * Check if request returns an OK status.
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void return_list_of_cars() throws Exception {
        given().car_exist_in_db();
        when().a_request(HttpMethod.GET, "/cars/all")
                .and()
                .is_sent();
        then().status_is_ok();
    }

    /**
     * Check if not found error is handled!
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void no_list_of_cars_return() throws Exception {
        given().no_car_exist_in_db();
        when().a_request(HttpMethod.GET, "/cars/all")
                .and()
                .is_sent();
        then().status_is_$(HttpStatus.NO_CONTENT);
    }

    /**
     * Check if request returns an OK status.
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void return_deleted_car() throws Exception {
        given().car_exist_in_db();
        when().a_request(HttpMethod.DELETE, "/cars/delete/{id}", given().getLastIdInserted())
                .and()
                .is_sent();
        then().status_is_$(HttpStatus.ACCEPTED);
    }

    /**
     * Check if not found error is handled!
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void no_cars_deleted() throws Exception {
        given().no_car_exist_in_db();
        when().a_request(HttpMethod.DELETE, "/cars/delete/{id}", 1)
                .and()
                .is_sent();
        then().status_is_$(HttpStatus.NO_CONTENT);
    }

    /**
     * check if car is created and request returns an OK status.
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void return_cars_created() throws Exception {
        var carToSave = CarFactory.createCarDTO();
        given().no_car_exist_in_db();
        when().a_request(HttpMethod.POST, "/cars/create")
                .and()
                .with_body(carToSave)
                .and()
                .is_sent();
        then().status_is_ok();
    }


    /**
     * Check if request returns an OK status.
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void return_updated_car() throws Exception {
        var carToSave = CarFactory.createCarDTO();
        given().car_exist_in_db();
        carToSave.setId(given().getLastIdInserted());
        when().a_request(HttpMethod.PUT, "/cars/update")
                .and()
                .with_body(carToSave)
                .and()
                .is_sent();
        then().status_is_ok();
    }

    /**
     * Check if not found error is handled!
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void return_no_updated_car() throws Exception {
        var carToSave = CarFactory.createCarDTO();
        carToSave.setId("1");
        given().no_car_exist_in_db();
        when().a_request(HttpMethod.PUT, "/cars/update")
                .and()
                .with_body(carToSave)
                .and()
                .is_sent();
        then().status_is_$(HttpStatus.NO_CONTENT);
    }

    /**
     * Check if a field matches the value
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void return_updated_field_matches() throws Exception {
        var carToSave = CarFactory.createCarDTO();
        given().car_exist_in_db();
        carToSave.setId(given().getLastIdInserted());
        when().a_request(HttpMethod.PUT, "/cars/update")
                .and()
                .with_body(carToSave)
                .and()
                .is_sent();
        then().status_is_ok().field_values_is_equal_to("carName", carToSave.getCarName());
    }

    /**
     * Check if request returns an OK status.
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void return__car() throws Exception {
        given().car_exist_in_db();
        when().a_request(HttpMethod.GET, "/cars/{id}", given().getLastIdInserted())
                .and()
                .is_sent();
        then().status_is_ok();
    }

    /**
     * Check if not found error is handled!
     *
     * @throws Exception in case matching goes wrong
     */
    @Test
    public void no_car_found() throws Exception {
        given().no_car_exist_in_db();
        when().a_request(HttpMethod.GET, "/cars/{id}", 1)
                .and()
                .is_sent();
        then().status_is_$(HttpStatus.NO_CONTENT);
    }
}
