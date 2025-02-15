package com.production.infrastructure.configuration;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queueOrders() {
        return QueueBuilder.durable("queue.orders")
                .withArgument("x-dead-letter-exchange", "dlx-exchange") // Configuração DLX
                .withArgument("x-dead-letter-routing-key", "dlx-queue.orders") // Chave de roteamento DLX
                .build();
    }

    @Bean
    public Queue queueDLX() {
        return new Queue("dlx-queue.orders", true); // Fila DLX para mensagens com erro
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlx-exchange");
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(queueDLX()).to(dlxExchange()).with("dlx-queue.orders");
    }
}
