package ru.dit.reactive.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.dit.model.order.OrderListResponse;
import ru.dit.model.order.OrderResponse;
import ru.dit.reactive.order.service.OrderService;
import ru.dit.reactive.server.order.OrderApi;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class OrderController implements OrderApi {

    private static final String DEFAULT_LIMIT = "10";

    private final OrderService orderService;

    @GetMapping("/order")
    public Mono<ResponseEntity<OrderResponse>> getOrder(ServerWebExchange exchange) {
        return orderService.getOrder()
                .map(ResponseEntity::ok)
                .doOnSuccess(rs -> log.info("Response Order Ok"))
                .onErrorResume(e -> {
                    log.error("Request failed", e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    @GetMapping("/orders")
    public Mono<ResponseEntity<OrderListResponse>> getLatestOrders(
            @RequestParam(defaultValue = DEFAULT_LIMIT) Integer limit,
            ServerWebExchange exchange
    ) {
        return orderService.getOrders(limit)
                .map(ResponseEntity::ok)
                .doOnSuccess(rs -> log.info("Response Orders Ok"))
                .onErrorResume(e -> {
                    log.error("Request failed", e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }
}
