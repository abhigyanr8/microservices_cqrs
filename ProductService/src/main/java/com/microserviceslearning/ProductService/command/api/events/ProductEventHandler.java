package com.microserviceslearning.ProductService.command.api.events;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.microserviceslearning.ProductService.command.api.data.Product;
import com.microserviceslearning.ProductService.command.api.data.ProductRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler
{
    private ProductRepo productRepo;
    public ProductEventHandler(ProductRepo productRepo)
    {
        this.productRepo = productRepo;
    }


    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent,product);
        productRepo.save(product);


    }

}
