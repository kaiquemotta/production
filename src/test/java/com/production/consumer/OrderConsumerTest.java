package com.production.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.production.application.consumer.OrderConsumer;
import com.production.application.processor.OrderProcessor;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderConsumerTest {

    @InjectMocks
    private OrderConsumer orderConsumer;

    @Mock
    private List<OrderProcessor> orderProcessors;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Channel channel;

    @Mock
    private Message message;

    @Mock
    private OrderProcessor orderProcessor;

    private Order order;
    private String messageContent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order(
                "orderId",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                Collections.emptyList()
        );
        messageContent = "{\"orderId\": \"orderId\", \"status\": \"PENDING\", \"date\": \"2025-02-15T12:00:00\", \"itemsOrder\": []}";
    }


    @Test
    void testNackMessage() throws IOException {
        long deliveryTag = 1L;
        ReflectionTestUtils.invokeMethod(orderConsumer, "nackMessage", channel, message, deliveryTag);
        verify(channel).basicReject(eq(deliveryTag), eq(false));
    }

    @Test
    void testMoveMessageToFailureQueue() throws IOException {
        long deliveryTag = 1L;
        doThrow(new IOException("Erro ao mover mensagem para fila de falhas")).when(channel).basicReject(eq(deliveryTag), eq(false));
        ReflectionTestUtils.invokeMethod(orderConsumer, "moveMessageToFailureQueue", channel, message, deliveryTag);
        verify(channel).basicReject(eq(deliveryTag), eq(false));
    }
}
