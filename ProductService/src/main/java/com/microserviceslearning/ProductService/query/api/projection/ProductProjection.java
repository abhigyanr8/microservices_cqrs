package com.microserviceslearning.ProductService.query.api.projection;

import com.microserviceslearning.ProductService.command.api.data.Product;
import com.microserviceslearning.ProductService.command.api.data.ProductRepo;
import com.microserviceslearning.ProductService.command.api.model.ProductRestModel;
import com.microserviceslearning.ProductService.query.api.queries.GetProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection
{
    private ProductRepo productRepo;
    public ProductProjection(ProductRepo productRepo)
    {
        this.productRepo = productRepo;
    }
    @QueryHandler
    public List<ProductRestModel> handle(GetProductQuery getProductQuery)
    {
        List<Product> products = productRepo.findAll();
        List<ProductRestModel> productRestModels = products.stream().map(product->ProductRestModel.builder().quantity(product.getQuantity()).name(product.getName()).price(product.getPrice()).build()).collect(Collectors.toList());
        return productRestModels;

    }
}