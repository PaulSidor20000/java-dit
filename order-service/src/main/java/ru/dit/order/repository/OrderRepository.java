package ru.dit.order.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import ru.dit.order.entity.OrderEntity;

import java.util.UUID;

public interface OrderRepository extends R2dbcRepository<OrderEntity, UUID> {

    Flux<OrderEntity> findAllByOrderByCreatedDesc(Pageable pageable);
}
