package car.com.Cars.controller.BasicModels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@JGivenStage
public class WhenBasicModel extends Stage<WhenBasicModel> {

    @ProvidedScenarioState
    ResultActions requestResult;

    @Autowired
    private MockMvc mockMvc;

    private MockHttpServletRequestBuilder requestBuilder;


    @Autowired
    private ObjectMapper jsonMapper;


    /**
     * Prepares a request
     *
     * @param httpMethod     give method
     * @param endpoint       value
     * @param pathParameters parameters
     * @return this
     */
    public WhenBasicModel a_request(HttpMethod httpMethod, String endpoint, Object... pathParameters) {
        requestBuilder = MockMvcRequestBuilders.request(httpMethod, endpoint, pathParameters);
        return self();
    }

    /**
     * Sets header parameters to request
     *
     * @param key   header key
     * @param value header value
     * @return this
     */
    public WhenBasicModel with_header_parameter(String key, String value) {
        requestBuilder.header(key, value);
        return self();
    }

    /**
     * Sets query parameter for request
     *
     * @param key   query parameter key
     * @param value query parameter value
     * @return this
     */
    public WhenBasicModel with_query_parameter(String key, String value) {
        requestBuilder.queryParam(key, value);
        return self();
    }

    /**
     * Sets query parameter for request
     *
     * @param body   body of the request
     * @return this
     */
    public WhenBasicModel with_body(Object body) throws JsonProcessingException {
        var jsonBody = jsonMapper.writeValueAsString(body);
        requestBuilder.content(jsonBody).contentType(MediaType.APPLICATION_JSON);
        return self();
    }

    /**
     * Request is sent
     *
     * @return this
     * @throws Exception in case something goes wrong sending request
     */
    public WhenBasicModel is_sent() throws Exception {
        requestResult = mockMvc.perform(requestBuilder);
        return self();
    }
}
