package car.com.Cars.config;

import com.github.fridujo.rabbitmq.mock.compatibility.MockConnectionFactoryFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuration to mock rabbit mq
 */
@TestConfiguration
public class MockRabbitMQConfig {

    /**
     * Mock the connection factory
     *
     * @return ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(MockConnectionFactoryFactory.build());
    }

}

