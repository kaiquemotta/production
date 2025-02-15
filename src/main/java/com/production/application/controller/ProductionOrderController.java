package com.production.application.controller;

import com.production.application.dto.request.ProductionOrderRequestDTO;
import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.application.mapper.ProductionOrderMapper;
import com.production.application.usecase.GetAllProductionOrdersInteractor;
import com.production.domain.entity.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/production-orders")
@Log4j2
public class ProductionOrderController {

    private final GetAllProductionOrdersInteractor getAllProductionOrdersUseCase;

    public ProductionOrderController(GetAllProductionOrdersInteractor getAllProductionOrdersUseCase) {
        this.getAllProductionOrdersUseCase = getAllProductionOrdersUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<ProductionOrderResponseDTO>> getAllOrders() {
        log.info("GET all Production orders");
        List<Order> ordersDomain = getAllProductionOrdersUseCase.getAllProductionOrders();
        List<ProductionOrderResponseDTO> response = ProductionOrderMapper.toProductionResponseList(ordersDomain);
        return ResponseEntity.ok(response);
    }
}
