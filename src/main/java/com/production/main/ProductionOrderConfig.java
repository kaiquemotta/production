package com.production.main;

import com.production.application.gateways.ProductionOrderGateway;
import com.production.application.processor.OrderProcessor;
import com.production.application.processor.ReceivedOrderProcessor;
import com.production.application.processor.SaveProductionOrderProcessor;
import com.production.application.usecase.ReceivedOrderInteractor;
import com.production.application.usecase.SaveProductionOrderInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductionOrderConfig {

    private final ReceivedOrderInteractor receivedOrderInteractor;
    private final SaveProductionOrderInteractor saveProductionOrderInteractor;

    public ProductionOrderConfig(ReceivedOrderInteractor receivedOrderInteractor, SaveProductionOrderInteractor saveProductionOrderInteractor) {
        this.receivedOrderInteractor = receivedOrderInteractor;
        this.saveProductionOrderInteractor = saveProductionOrderInteractor;
    }

    @Bean
    public OrderProcessor receivedOrderProcessor(ReceivedOrderInteractor receivedOrderInteractor) {
        return new ReceivedOrderProcessor(receivedOrderInteractor);
    }

    @Bean
    public OrderProcessor saveOrderProcessor(ReceivedOrderInteractor receivedOrderInteractor) {
        return new SaveProductionOrderProcessor(saveProductionOrderInteractor);
    }


//    @Bean
//    ReceivedOrderInteractor receivedOrderInteractor(ProductionOrderGateway productionOrderGateway){
//        return new ReceivedOrderInteractor(productionOrderGateway);
//    }

}
