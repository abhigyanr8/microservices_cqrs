package com.microserviceslearning.ProductService.command.api.aggregate;

import com.microserviceslearning.ProductService.command.api.commands.CreateProductCommand;
import com.microserviceslearning.ProductService.command.api.events.ProductCreatedEvent;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate
{
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate(CreateProductCommand createProductCommand)
    {
          //you can perform all the validations here
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);

    }
    public ProductAggregate()
    {

    }
}
