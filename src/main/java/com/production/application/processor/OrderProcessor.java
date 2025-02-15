package com.production.application.processor;

import com.production.domain.entity.Order;

public interface OrderProcessor {
    void process(Order order);
}