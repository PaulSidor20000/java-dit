package ru.dit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.dit.client.order.OrderApi;
import ru.dit.order.config.FeignHttpClientConfig;

@FeignClient(
        name = "http://localhost",
        url = "#{ '${client.mock.url}:' + '${client.mock.port}' + '${client.mock.api.version}' }",
        configuration = FeignHttpClientConfig.class
)
public interface MockClient extends OrderApi {
}
