package com.production.application.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.production.application.usecase.ReceivedOrderUseCase;
import com.production.domain.entity.Order;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private final ReceivedOrderUseCase useCase;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "queue.orders")
    public void receivedMessage(String mensagem) {
        try {
            Order order = objectMapper.readValue(mensagem, Order.class);
            logger.info("📥 Order received: {}", order);
            useCase.processarPedido(order);
            logger.info("✅ Pedido processado com sucesso: {}", order.orderId());
        } catch (Exception e) {
            logger.error("❌ Erro ao processar pedido: {}", e.getMessage(), e);
        }
    }
}
