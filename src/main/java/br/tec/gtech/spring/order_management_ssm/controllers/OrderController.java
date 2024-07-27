package br.tec.gtech.spring.order_management_ssm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tec.gtech.spring.order_management_ssm.services.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public String getAppStatus() {
        return "App is online";
    }

    @PostMapping("new")
    public String newOrder() {
        orderService.newOrder();
        return "new order";
    }

    @PostMapping("pay")
    public String payOrder() {
        orderService.payOrder();
        return "pay order";
    }

    @PostMapping("ship")
    public String shipOrder() {
        orderService.shipOrder();
        return "ship order";
    }

    @PostMapping("complete")
    public String completeOrder() {
        orderService.completeOrder();
        return "complete order";
    }

    
    
    
}
