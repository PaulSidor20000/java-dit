package ru.dit.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dit.model.order.OrderListResponse;
import ru.dit.model.order.OrderResponse;
import ru.dit.order.service.OrderService;
import ru.dit.server.order.OrderApi;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController implements OrderApi {

    private static final String DEFAULT_LIMIT = "10";

    private final OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<OrderResponse> getOrder() {
        return ResponseEntity.ok(orderService.getOrder());
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderListResponse> getLatestOrders(
            @RequestParam(defaultValue = DEFAULT_LIMIT) Integer limit) {
        return ResponseEntity.ok(orderService.getOrders(limit));
    }
}
