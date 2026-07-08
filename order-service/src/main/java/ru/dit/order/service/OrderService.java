package ru.dit.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dit.model.order.OrderListResponse;
import ru.dit.model.order.OrderResponse;
import ru.dit.order.client.MockClient;
import ru.dit.order.mapper.OrderMapper;
import ru.dit.order.service.dao.OrderDaoService;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final MockClient mockClient;
    private final OrderMapper orderMapper;
    private final OrderDaoService orderDaoService;

    public OrderResponse getOrder() {
        var orderResponse = mockClient.getOrder().getBody();
        var savedOrder = orderDaoService.saveOrder(orderResponse);

        return OrderResponse.builder()
                .rqId(UUID.randomUUID())
                .timestamp(OffsetDateTime.now())
                .order(savedOrder)
                .build();
    }

    public OrderListResponse getOrders(Integer limit) {
        var lastOrdersWithLimit = orderDaoService.findLastOrdersWithLimit(limit);

        return orderMapper.mapToSuccessOrderListResponse(lastOrdersWithLimit);
    }
}
