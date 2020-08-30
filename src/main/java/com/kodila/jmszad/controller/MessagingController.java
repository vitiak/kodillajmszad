package com.kodila.jmszad.controller;

import com.kodila.jmszad.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class MessagingController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(path = "/process")
    public void processMessage(@RequestParam String message) {
        jmsTemplate.convertAndSend("queue-test", message);
    }

    @GetMapping(path = "/order")
    public Order getOrder() {
        Order order = new Order("prod1", 2);
        jmsTemplate.convertAndSend("queue-test",order.getProduct() + " " + order.getQuantity());
        return order;
    }

}
