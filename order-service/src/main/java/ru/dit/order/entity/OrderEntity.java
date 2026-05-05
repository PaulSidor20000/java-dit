package ru.dit.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Table(name = "orders")
public class OrderEntity extends CommonEntity {

    @Column("product_id")
    private UUID productId;

    private Integer quantity;

    private LocalDate shipped;

    private String status;
}
