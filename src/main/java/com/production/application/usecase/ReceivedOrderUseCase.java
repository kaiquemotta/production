package com.production.application.usecase;

import com.production.domain.entity.Order;

public interface ReceivedOrderUseCase {

    void processOrder(Order order);

}