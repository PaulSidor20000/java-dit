package ru.dit.mock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.dit.model.order.Order;
import ru.dit.model.order.OrderResponse;
import ru.dit.reactive.server.order.OrderApi;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MockOrderController implements OrderApi {

    public static final int DELAY = 200;

    @Override
    @GetMapping("/order")
    public Mono<ResponseEntity<OrderResponse>> getOrder(ServerWebExchange exchange) {
        var statusEnum = Order.StatusEnum.values()[new Random().nextInt(Order.StatusEnum.values().length)];
        var now = OffsetDateTime.now();

        return Mono.fromSupplier(() -> ResponseEntity.ok(
                        new OrderResponse().toBuilder()
                                .rqId(UUID.randomUUID())
                                .timestamp(now)
                                .order(new Order().toBuilder()
                                        .productId(UUID.randomUUID())
                                        .status(statusEnum)
                                        .build())
                                .build()))
                .delayElement(Duration.ofMillis(DELAY))
                .doOnSuccess(rs -> log.info("Response Order Ok"))
                .doOnError(e -> log.error("Request failed", e));
    }
}
