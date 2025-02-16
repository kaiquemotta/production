package com.production.controller;


import com.production.application.controller.ProductionOrderController;
import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.application.usecase.GetAllProductionOrdersInteractor;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

public class ProductionOrderControllerTestSteps {

    @Mock
    private GetAllProductionOrdersInteractor getAllProductionOrdersUseCase;

    @InjectMocks
    private ProductionOrderController controller;

    private ResponseEntity<List<ProductionOrderResponseDTO>> response;

    @Given("que o sistema possui ordens de produção cadastradas")
    public void queOSistemaPossuiOrdensDeProducaoCadastradas() {

        ItemOrder itemOrder2 = new ItemOrder(2L, "Description2", "Type2", "Observation2");
        Order order = new Order("order123", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(itemOrder2));
        Order order2 = new Order("order123", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(itemOrder2));
        List<Order> mockOrders = List.of(
                order,order2
        );
        MockitoAnnotations.openMocks(this);
        when(getAllProductionOrdersUseCase.getAllProductionOrders())
                .thenReturn(mockOrders);
        System.out.println("Mock retornando: " + mockOrders.size() + " ordens"); // Debug

    }

    @When("uma requisição GET é feita para {string}")
    public void umaRequisicaoGETEFFeitaPara(String endpoint) {
        response = controller.getAllOrders();
    }

    @Then("a resposta deve ter o status {int}")
    public void aRespostaDeveTerOStatus(int status) {
        Assertions.assertEquals(status, response.getStatusCode().value());
    }

    @Then("a resposta deve conter a lista de ordens de produção")
    public void aRespostaDeveConterAListaDeOrdensDeProducao() {
        Assertions.assertFalse(response.getBody().isEmpty());
        Assertions.assertEquals(2, response.getBody().size());
    }
}
