package com.example.LABRABBITS2.tests;

import org.junit.jupiter.api.Order;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testSaveOrder() {
        Order order = new Order("Pizza", "Pending");
        Order savedOrder = orderService.saveOrder(order);
        assertNotNull(savedOrder.getId());
    }

    @Test
    public void testFindOrderById() {
        Order order = new Order("Sushi", "Pending");
        Order savedOrder = orderService.saveOrder(order);
        Order foundOrder = orderService.findOrderById(savedOrder.getId());
        assertEquals("Sushi", foundOrder.getMeal());
    }

    @Test
    public void testFindAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        assertNotNull(orders);
        assertTrue(orders.size() >= 0); // Pode ser vazio se não houver registros
    }

    @Test
    public void testGetMeal() {
        Order order = new Order("Hamburguer", "Pending");
        Order savedOrder = orderService.saveOrder(order);
        String meal = orderService.getMeal(savedOrder.getId());
        assertEquals("Hamburguer", meal);
    }

    @Test
    public void testGetStatus() {
        Order order = new Order("Salad", "In Progress");
        Order savedOrder = orderService.saveOrder(order);
        String status = orderService.getStatus(savedOrder.getId());
        assertEquals("In Progress", status);
    }
}