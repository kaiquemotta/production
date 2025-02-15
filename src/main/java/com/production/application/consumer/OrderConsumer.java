package com.production.application.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.production.application.processor.OrderProcessor;
import com.production.application.usecase.ReceivedOrderInteractor;
import com.production.application.usecase.SaveProductionOrderInteractor;
import com.production.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private final ObjectMapper objectMapper;
    private final List<OrderProcessor> orderProcessors;

    @RabbitListener(queues = "queue.orders")
    public void receivedMessage(String mensagem) {
        try {
            Order order = objectMapper.readValue(mensagem, Order.class);
            logger.info("📥 Pedido recebido: {}", order);

            for (OrderProcessor processor : orderProcessors) {
                processor.process(order);
            }

            logger.info("✅ Pedido processado com sucesso: {}", order.orderId());
        } catch (JsonProcessingException e) {
            logger.error("🚨 Erro ao desserializar mensagem: {}", mensagem, e);
        } catch (Exception e) {
            logger.error("❌ Erro ao processar pedido: {}", e.getMessage(), e);
            throw e;
        }
    }
}
