package car.com.Cars.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * this class is created to test car controller endpoints
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    /**
     * Testing if a car that is not found is handled with the specific message
     * Requesting GET Method /cars/1
     *
     * @throws Exception throws Exception
     */
    @Test
    public void returnErrorMessage_whenRequesting_aCar_thatDontExists() throws Exception {
        var result = this.mvc.perform(MockMvcRequestBuilders.get("/cars/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message").value("Car requested could not be found! /cars/1")).andReturn();

    }

    /**
     * Testing if the request returns OK status and the carName of first car matches VW
     * Requesting GET Method /cars/9
     *
     * @throws Exception throws Exception
     */
    @Test
    public void returnOk_and_CarExists() throws Exception {
        var result = this.mvc.perform(MockMvcRequestBuilders.get("/cars/10").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$.carName").value("VW")).andReturn();

    }


    /**
     * Testing if the request returns OK status and if at least one car exists
     * Requesting GET Method /cars/all
     *
     * @throws Exception throws Exception
     */
    @Test
    public void returnOk_and_AtLeastOneCarExists() throws Exception {
        var result = this.mvc.perform(MockMvcRequestBuilders.get("/cars/all").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$.cars[0]").isNotEmpty()).andReturn();

    }


    /**
     * Testing if the request returns OK status and if at least one car exists
     * Requesting GET Method /cars/9
     *
     * @throws Exception throws Exception
     */
    @Test
    public void returnCarAfterDeleting() throws Exception {
        var result = this.mvc.perform(MockMvcRequestBuilders.delete("/cars/delete/9").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.carName").isNotEmpty()).andReturn();

    }

    /**
     * Testing if a car that is not found is handled with the specific message while deleting
     * Requesting Delete Method /cars/delete/1
     *
     * @throws Exception throws Exception
     */
    @Test
    public void returnErrorMessage_IfDeletingCar_WentWrong() throws Exception {
        var result = this.mvc.perform(MockMvcRequestBuilders.delete("/cars/delete/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message").value("No records found to be deleted! /cars/delete/1")).andReturn();

    }

}
