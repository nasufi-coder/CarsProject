package car.com.Cars.config;

import car.com.Cars.controller.CarDetailsController;
import com.baeldung.openapi.api.CarsApi;
import com.tngtech.jgiven.integration.spring.EnableJGiven;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@EnableJGiven
public class JGivenConfig {
}
