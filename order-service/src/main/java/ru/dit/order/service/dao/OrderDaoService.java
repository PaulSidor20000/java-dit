package ru.dit.order.service.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dit.model.order.Order;
import ru.dit.order.entity.OrderEntity;
import ru.dit.order.mapper.OrderMapper;
import ru.dit.order.repository.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDaoService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public Mono<OrderEntity> saveOrder(OrderEntity entity) {
        return orderRepository.save(entity);
    }

    @Transactional
    public Flux<Order> findLastOrdersWithLimit(Integer limit) {
        return orderRepository.findAllByOrderByCreatedDesc(Pageable.ofSize(limit))
                .map(orderMapper::map);
    }
}
