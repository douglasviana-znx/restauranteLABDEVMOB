package com.example.LABRABBITS2;
import com.example.LABRABBITS2.model.Order;
import com.example.LABRABBITS2.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Consumer {

    private final OrderRepository orderRepository;

    public Consumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = "${spring.queue.pizza}")
    public void consumePizza(String meal) throws InterruptedException {
        processOrder(meal, "Cozinha de Pizza");
    }

    @RabbitListener(queues = "${spring.queue.hamburger}")
    public void consumeHamburger(String meal) throws InterruptedException {
        processOrder(meal, "Cozinha de Hamburger");
    }

    @RabbitListener(queues = "${spring.queue.sushi}")
    public void consumeSushi(String meal) throws InterruptedException {
        processOrder(meal, "Cozinha de Sushi");
    }

    @RabbitListener(queues = "${spring.queue.salad}")
    public void consumeSalad(String meal) throws InterruptedException {
        processOrder(meal, "Cozinha de Salada");
    }

    @RabbitListener(queues = "${spring.queue.tacos}")
    public void consumeTacos(String meal) throws InterruptedException {
        processOrder(meal, "Cozinha de Tacos");
    }

    private void processOrder(String meal, String kitchen) throws InterruptedException {
        System.out.println(kitchen + " está preparando: " + meal);
        Thread.sleep((long) (2000 + Math.random() * 3000)); // Simula tempo de preparo entre 2 e 5 segundos

        // Atualizar status no banco
        Optional<Order> order = orderRepository.findById(findOrderId(meal));
        order.ifPresent(o -> {
            o.setStatus("FINALIZADO");
            orderRepository.save(o);
            System.out.println(kitchen + " finalizou o pedido: " + meal);
        });
    }

    private Long findOrderId(String meal) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getMeal().equals(meal) && order.getStatus().equals("ENVIADO"))
                .findFirst()
                .map(Order::getId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}