package com.samuelfsd.br.commerce.entities;

import com.samuelfsd.br.commerce.Enum.OrderStatus;
import com.samuelfsd.br.commerce.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "tb_order")
public class Order extends AbstractEntity {
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;


    public Order (){}

    public Order(Instant moment, OrderStatus status, User client) {
        super();
        this.moment = moment;
        this.status = status;
        this.client = client;
    }
}

