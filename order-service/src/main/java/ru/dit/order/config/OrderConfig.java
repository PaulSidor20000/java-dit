package ru.dit.order.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import ru.dit.order.client.MockClient;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Configuration
@EnableR2dbcAuditing
@RequiredArgsConstructor
public class OrderConfig {

    @Value("${client.mock.url}")
    private String mockClientUrl;

    @Value("${client.mock.port}")
    private String mockClientPort;

    @Value("${client.mock.api.version}")
    private String mockClientApiVersion;

    @Bean
    public MockClient mockApiClient() {
        var basePath = "http://" + mockClientUrl + ":" + mockClientPort + mockClientApiVersion;
        var client = new MockClient();
        client.setBasePath(basePath);

        return client;
    }

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now(ZoneOffset.UTC));
    }
}
