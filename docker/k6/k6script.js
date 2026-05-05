import http from 'k6/http';

export const options = {
  scenarios: {
    orders_create: {
      executor: 'constant-arrival-rate',
      rate: 200, // RPS
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 200,
      exec: 'createOrder',
    },
    orders_get: {
      executor: 'constant-arrival-rate',
      rate: 200, // RPS
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 200,
      exec: 'getOrders',
    },
  },
};

export function createOrder() {
  http.get('http://order-service:9089/api/v1/order');
}

export function getOrders() {
  http.get('http://order-service:9089/api/v1/orders');
}