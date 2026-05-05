package ru.dit.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommonEntity {

    @Id
    private UUID id;

    @CreatedDate
    private OffsetDateTime created;

    @LastModifiedDate
    private OffsetDateTime updated;

    @Version
    private Long version;
}
