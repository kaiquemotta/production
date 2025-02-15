package com.production.controller;


import com.production.application.controller.ProductionOrderController;
import com.production.application.usecase.GetAllProductionOrdersInteractor;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)  // Garante a inicialização dos mocks do Mockito
class ProductionOrderControllerTest {

    @Mock
    private GetAllProductionOrdersInteractor getAllProductionOrdersUseCase;

    @InjectMocks
    private ProductionOrderController productionOrderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productionOrderController).build();
    }

    @Test
    void testGetAllOrders() throws Exception {
        ItemOrder item1 = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        ItemOrder item2 = new ItemOrder(2L, "Product 2", "Type B", "Observation 2");

        Order order = new Order(
                "123",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                List.of(item1, item2)
        );

        List<Order> orders = Collections.singletonList(order);

        when(getAllProductionOrdersUseCase.getAllProductionOrders()).thenReturn(orders);

        mockMvc.perform(get("/production-orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());
    }
}
