package ru.dit.mock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class MockServiceApplication {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public static void main(String[] args) {
        SpringApplication.run(MockServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logEndpoints() {
        requestMappingHandlerMapping.getHandlerMethods()
                .forEach((info, method) ->
                        log.info("{} -> {}.{}",
                                info,
                                method.getBeanType().getSimpleName(),
                                method.getMethod().getName())
                );
    }
}
