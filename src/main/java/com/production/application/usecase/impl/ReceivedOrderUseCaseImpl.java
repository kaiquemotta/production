package com.production.application.usecase.impl;


import com.production.application.usecase.ReceivedOrderUseCase;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.domain.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReceivedOrderUseCaseImpl implements ReceivedOrderUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ReceivedOrderUseCaseImpl.class);
    private final OrderRepository orderRepository;

    public ReceivedOrderUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void processOrder(Order order) {
        logger.info("📥 Processando pedido: {}", order);
        order.itemsOrder().forEach(this::processarItem);
        orderRepository.save(order);
        logger.info("✅ Pedido processado e salvo: {}", order.orderId());
    }

    private void processarItem(ItemOrder item) {
        switch (item.type().toUpperCase()) {
            case "COMIDA":
                logger.info("👨‍🍳 Preparando comida: {}", item.description());
                break;
            case "BEBIDA":
                logger.info("🥤 Preparando bebida: {}", item.description());
                break;
            default:
                logger.warn("⚠️ Tipo desconhecido: {}", item.type());
                break;
        }
    }
}
