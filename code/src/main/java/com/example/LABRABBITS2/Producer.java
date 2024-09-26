package com.example.LABRABBITS2;
import com.example.LABRABBITS2.model.Order;
import com.example.LABRABBITS2.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final OrderRepository orderRepository;

    @Value("${spring.queue.pizza}")
    private String pizzaQueue;

    @Value("${spring.queue.hamburger}")
    private String hamburgerQueue;

    @Value("${spring.queue.sushi}")
    private String sushiQueue;

    @Value("${spring.queue.salad}")
    private String saladQueue;

    @Value("${spring.queue.tacos}")
    private String tacosQueue;

    public Producer(RabbitTemplate rabbitTemplate, OrderRepository orderRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderRepository = orderRepository;
    }

    public void sendOrder(String meal) {
        Order order = new Order(meal, "ENVIADO");
        orderRepository.save(order);

        switch (meal) {
            case "Pizza":
                rabbitTemplate.convertAndSend(pizzaQueue, meal);
                break;
            case "Hamburger":
                rabbitTemplate.convertAndSend(hamburgerQueue, meal);
                break;
            case "Sushi":
                rabbitTemplate.convertAndSend(sushiQueue, meal);
                break;
            case "Salad":
                rabbitTemplate.convertAndSend(saladQueue, meal);
                break;
            case "Tacos":
                rabbitTemplate.convertAndSend(tacosQueue, meal);
                break;
        }
    }
}