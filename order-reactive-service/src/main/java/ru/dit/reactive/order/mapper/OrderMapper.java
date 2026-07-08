package ru.dit.reactive.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dit.model.order.Order;
import ru.dit.model.order.OrderListResponse;
import ru.dit.model.order.OrderResponse;
import ru.dit.reactive.order.config.MapperConfiguration;
import ru.dit.reactive.order.entity.OrderEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(config = MapperConfiguration.class)
public interface OrderMapper {

    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productId", source = "order.productId")
    @Mapping(target = "shipped", source = "order.shipped")
    @Mapping(target = "quantity", source = "order.quantity")
    @Mapping(target = "status", source = "order.status")
    OrderEntity map(OrderResponse orderResponse);

    Order map(OrderEntity orderEntity);

    default OrderListResponse mapToSuccessOrderListResponse(List<Order> orders) {
        return OrderListResponse.builder()
                .rqId(UUID.randomUUID())
                .timestamp(OffsetDateTime.now())
                .status(OrderListResponse.StatusEnum.SUCCESS)
                .orders(orders)
                .build();
    }
}
