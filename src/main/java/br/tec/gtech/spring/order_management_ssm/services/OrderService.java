package br.tec.gtech.spring.order_management_ssm.services;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import br.tec.gtech.spring.order_management_ssm.state_machine.OrderEvents;
import br.tec.gtech.spring.order_management_ssm.state_machine.OrderStates;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory;

    private StateMachine<OrderStates, OrderEvents> stateMachine;

    public OrderService(StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }

    public void newOrder() {
        initOrderSaga();
        validateOrder();
    }

    private void initOrderSaga() {
        System.out.println("Initializing order saga...");
        stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.startReactively().subscribe();
        System.out.println("Final state: " + stateMachine.getState().getId());
    }

    private void validateOrder() {
        System.out.println("Validating order...");
        stateMachine.sendEvent(Mono.just(
                MessageBuilder.withPayload(OrderEvents.VALIDATE)
                        // .setHeader("orderId", 1)
                        .build()))
                .subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());
    }

    public void payOrder() {
        System.out.println("Paying order...");
        stateMachine.sendEvent(Mono.just(
                MessageBuilder.withPayload(OrderEvents.PAY)
                        // .setHeader("orderId", 1)
                        .build()))
                .subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());
    }

    public void shipOrder() {
        System.out.println("Shipping order...");
        stateMachine.sendEvent(Mono.just(
                MessageBuilder.withPayload(OrderEvents.SHIP)
                        // .setHeader("orderId", 1)
                        .build()))
                .subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());
    }

    public void completeOrder() {
        System.out.println("Completing order...");
        stateMachine.sendEvent(Mono.just(
                MessageBuilder.withPayload(OrderEvents.SHIP)
                        // .setHeader("orderId", 1)
                        .build()))
                .subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());

        stopOrderSaga();
    }

    private void stopOrderSaga() {
        System.out.println("Stopping saga...");
        stateMachine.stopReactively().subscribe();
    }

}
