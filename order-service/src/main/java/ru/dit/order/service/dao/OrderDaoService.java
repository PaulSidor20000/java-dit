package ru.dit.order.service.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dit.model.order.Order;
import ru.dit.model.order.OrderResponse;
import ru.dit.order.mapper.OrderMapper;
import ru.dit.order.repository.OrderRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDaoService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public Order saveOrder(OrderResponse order) {
        var orderEntity = orderMapper.map(order);
        var saved = orderRepository.save(orderEntity);

        return orderMapper.map(saved);
    }

    @Transactional
    public List<Order> findLastOrdersWithLimit(Integer limit) {
        return orderRepository.findAllByOrderByCreatedDesc(Pageable.ofSize(limit)).stream()
                .map(orderMapper::map)
                .toList();
    }
}
