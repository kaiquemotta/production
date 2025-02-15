package com.production.application.processor;

import com.production.application.usecase.SaveProductionOrderInteractor;
import com.production.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public class SaveProductionOrderProcessor implements OrderProcessor {
    private final SaveProductionOrderInteractor saveInteractor;

    public SaveProductionOrderProcessor(SaveProductionOrderInteractor saveInteractor) {
        this.saveInteractor = saveInteractor;
    }

    @Override
    public void process(Order order) {
        saveInteractor.saveProductionOrder(order);
    }
}
