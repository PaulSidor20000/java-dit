package ru.dit.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.dit.model.order.OrderListResponse;
import ru.dit.model.order.OrderResponse;
import ru.dit.order.client.MockClient;
import ru.dit.order.mapper.OrderMapper;
import ru.dit.order.service.dao.OrderDaoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final MockClient mockClient;
    private final OrderMapper orderMapper;
    private final OrderDaoService orderDaoService;

    public Mono<OrderResponse> getOrder() {
        return mockClient.getOrder()
                .flatMap(response ->
                        orderDaoService.saveOrder(orderMapper.map(response))
                                .thenReturn(response)
                );
    }

    public Mono<OrderListResponse> getOrders(Integer limit) {
        return orderDaoService.findLastOrdersWithLimit(limit)
                .collectList()
                .map(orderMapper::mapToSuccessOrderListResponse);
    }
}
