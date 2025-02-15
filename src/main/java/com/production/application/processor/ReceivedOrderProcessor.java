package com.production.application.processor;

import com.production.application.usecase.ReceivedOrderInteractor;
import com.production.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class ReceivedOrderProcessor implements OrderProcessor {

    private final ReceivedOrderInteractor receivedInteractor;

    @Override
    public void process(Order order) {
        receivedInteractor.processOrder(order);
    }
}