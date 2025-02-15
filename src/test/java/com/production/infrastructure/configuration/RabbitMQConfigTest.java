package com.production.infrastructure.configuration;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RabbitMQConfigTest {

    @Test
    public void testQueueOrdersBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        Queue queueOrders = context.getBean("queueOrders", Queue.class);
        assertNotNull(queueOrders, "Queue 'queueOrders' should not be null");
        assert(queueOrders.getName()).equals("queue.orders");
        context.close();
    }

    @Test
    public void testQueueDLXBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        Queue queueDLX = context.getBean("queueDLX", Queue.class);
        assertNotNull(queueDLX, "Queue 'queueDLX' should not be null");
        assert(queueDLX.getName()).equals("dlx-queue.orders");
        context.close();
    }
}
