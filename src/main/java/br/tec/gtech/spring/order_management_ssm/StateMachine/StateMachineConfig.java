package br.tec.gtech.spring.order_management_ssm.StateMachine;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents>{

@Override
public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
    states.withStates()
    .initial(OrderStates.NEW)
    .states(EnumSet.allOf(OrderStates.class))
    .end(OrderStates.COMPLETED)
    .end(OrderStates.CANCELLED);
    
}


    
}
