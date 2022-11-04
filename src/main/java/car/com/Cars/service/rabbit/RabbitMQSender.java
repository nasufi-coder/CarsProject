package car.com.Cars.service.rabbit;

import car.com.Cars.model.rabbit.QueueCar;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${cars.rabbitmq.exchange}")
    private String exchange;

    @Value("${cars.rabbitmq.routingKey}")
    private String routingKey;

    public void send(QueueCar message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Send msg = " + message);
    }
}