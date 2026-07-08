package ru.dit.reactive.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ru.dit.model.order.OrderResponse;
import ru.dit.reactive.invoker.order.ApiClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockClient extends ApiClient {

    @Value("${client.mock.api.order}")
    private String mockClientApiOrder;

    /**
     * Get order information
     * Returns order response with status and possible error details
     * <p><b>200</b> - Successful response
     * <p><b>400</b> - Bad Request
     * <p><b>404</b> - Order not found
     * <p><b>429</b> - Too many requests
     *
     * @return OrderResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<OrderResponse> getOrder() throws WebClientResponseException {
        var localVarReturnType = new ParameterizedTypeReference<OrderResponse>() {
        };
        return getOrderRequestCreation().bodyToMono(localVarReturnType);
    }

    private WebClient.ResponseSpec getOrderRequestCreation() throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final List<MediaType> localVarAccept = selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {};
        final MediaType localVarContentType = selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[]{};

        ParameterizedTypeReference<OrderResponse> localVarReturnType = new ParameterizedTypeReference<OrderResponse>() {
        };
        return invokeAPI(mockClientApiOrder, HttpMethod.GET, pathParams, queryParams, postBody, headerParams,
                cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }
}
