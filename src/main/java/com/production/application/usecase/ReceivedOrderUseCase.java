package com.production.application.usecase;

import com.production.domain.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class ReceivedOrderUseCase {

    public void processarPedido(Order order) {
        // Aqui você pode adicionar regras de negócio
        System.out.println("📥 Pedido recebido: " + order);
        order.itemsOrder().forEach(item -> {
            if ("COMIDA".equalsIgnoreCase(item.type())) {
                System.out.println("👨‍🍳 Preparando comida: " + item.description());
            } else if ("BEBIDA".equalsIgnoreCase(item.type())) {
                System.out.println("🥤 Preparando bebida: " + item.description());
            }
        });
    }
}
