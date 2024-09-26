package com.example.LABRABBITS2.service;
import com.example.LABRABBITS2.repository.OrderRepository;
import com.example.LABRABBITS2.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Encontra um pedido por ID, usando a função findOrderId
    public Order findOrderById(Long findOrderId) {
        Optional<Order> optionalOrder = orderRepository.findById(findOrderId);
        return optionalOrder.orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
    }

    // Salva um pedido
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Retorna todos os pedidos
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // Obtém o nome da refeição de um pedido
    public String getMeal(Long orderId) {
        Order order = findOrderById(orderId);
        return order.getMeal();
    }

    // Obtém o status de um pedido
    public String getStatus(Long orderId) {
        Order order = findOrderById(orderId);
        return order.getStatus();
    }

    // Método auxiliar para encontrar o ID de um pedido com base na refeição
    public Long findOrderId(String meal) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getMeal().equals(meal))
                .findFirst()
                .map(Order::getId)
                .orElseThrow(() -> new RuntimeException("Refeição não encontrada!"));
    }
}