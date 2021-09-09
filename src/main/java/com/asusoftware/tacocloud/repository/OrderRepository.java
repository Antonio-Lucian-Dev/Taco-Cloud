package com.asusoftware.tacocloud.repository;

import com.asusoftware.tacocloud.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
