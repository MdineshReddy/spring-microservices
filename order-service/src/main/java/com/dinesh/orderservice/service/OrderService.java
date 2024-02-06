package com.dinesh.orderservice.service;

import com.dinesh.orderservice.dto.OrderRequest;
import com.dinesh.orderservice.entity.Order;
import com.dinesh.orderservice.entity.OrderLineItem;
import com.dinesh.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest) {
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList().stream()
                .map(o->OrderLineItem.builder()
                        .price(o.getPrice())
                        .quantity(o.getQuantity())
                        .skuCode(o.getSkuCode())
                        .build())
                .toList();
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemList(orderLineItems).build();
        orderRepository.save(order);
        return "Order Placed Successfully";
    }
}
