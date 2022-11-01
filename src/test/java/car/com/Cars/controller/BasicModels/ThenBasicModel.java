package car.com.Cars.controller.BasicModels;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tngtech.jgiven.Stage;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

@JGivenStage
public class ThenBasicModel extends Stage<ThenBasicModel> {

    @ExpectedScenarioState
    ResultActions requestResult;

    @Autowired
    private ObjectMapper jsonMapper;

    /**
     * Checks that status of response is okay
     *
     * @return this
     * @throws Exception in case something is wrong during matching
     */
    public ThenBasicModel status_is_ok() throws Exception {
        requestResult.andExpect(status().isOk());
        return self();
    }

    /**
     * @param fieldName  name of the json field that is going to be compared
     * @param fieldValue value that we are expecting
     * @return this
     * @throws Exception in case matching goes wrong
     */
    public ThenBasicModel field_values_is_equal_to(String fieldName, String fieldValue) throws Exception {
        requestResult.andExpect(status().isOk()).andExpect(jsonPath("$." + fieldName).value(fieldValue));
        return self();
    }

    /**
     * Checks http status of response
     *
     * @param httpStatus expected status
     * @return this
     * @throws Exception in case matching goes wrong
     */
    public ThenBasicModel status_is_$(HttpStatus httpStatus) throws Exception {
        requestResult.andExpect(status().is(httpStatus.value()));
        return self();
    }
}
