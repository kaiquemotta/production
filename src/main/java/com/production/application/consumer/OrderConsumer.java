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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.rabbitmq.client.Channel; // Importação do Channel
import org.springframework.amqp.core.Message;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private final ObjectMapper objectMapper;
    private final List<OrderProcessor> orderProcessors;

    @RabbitListener(queues = "queue.orders")
    public void receivedMessage(String mensagem, Channel channel, Message message) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();  // Pegue o deliveryTag da mensagem
        try {
            Order order = objectMapper.readValue(mensagem, Order.class);
            logger.info("📥 Pedido recebido: {}", order);
            for (OrderProcessor processor : orderProcessors) {
                processor.process(order);
            }
            channel.basicAck(deliveryTag, false);
            logger.info("✅ Pedido processado com sucesso: {}", order.orderId());

        } catch (JsonProcessingException e) {
            logger.error("🚨 Erro ao desserializar mensagem: {}", mensagem, e);
            nackMessage(channel, message, deliveryTag);
        } catch (Exception e) {
            logger.error("❌ Erro ao processar pedido: {}", e.getMessage(), e);
            nackMessage(channel, message, deliveryTag);
            throw e;
        }
    }

    private void nackMessage(Channel channel, Message message, long deliveryTag) {
                moveMessageToFailureQueue(channel, message, deliveryTag);
    }

    private void moveMessageToFailureQueue(Channel channel, Message message, long deliveryTag) {
        try {
            channel.basicReject(deliveryTag, false); // Ou basicNack com requeue=false
            logger.error("Mensagem movida para fila de falhas após tentativas excessivas.");
        } catch (IOException ex) {
            logger.error("Erro ao mover mensagem para fila de falhas: {}", ex.getMessage(), ex);
        }
    }


}

