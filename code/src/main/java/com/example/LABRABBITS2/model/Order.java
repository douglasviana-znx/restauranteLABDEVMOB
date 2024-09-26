package com.example.LABRABBITS2.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meal;
    private String status;

    // Construtores
    public Order() {}

    public Order(String meal, String status) {
        this.meal = meal;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}