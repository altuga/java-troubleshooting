package com.example.controllers;

import com.example.model.dtos.TotalCostResponse;
import com.example.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }




    @GetMapping("/total/costs/v1")
    //@SchemaMapping(typeName = "Query", fieldName = "bookById")
    public TotalCostResponse totalCostsv1() {

        return productService.getTotalCosts();

    }

    @GetMapping("/total/costs/v2")
    //@SchemaMapping(typeName = "Query", fieldName = "bookById")
    public CompletableFuture<TotalCostResponse> totalCostsv2() {

        /**
         *
         */

        return productService.getTotalCostsAsyncWay().thenApply(value -> {
            if (value != null) {
                System.out.println("app is finished " + Thread.currentThread().getName());
                return value;
            } else {
                System.out.println("app is not finished");
                return null;
            }
        });


    }
    @GetMapping("/total/costs/v3")
    //@SchemaMapping(typeName = "Query", fieldName = "bookById")
    public CompletableFuture<TotalCostResponse> totalCostsv3() {

        return productService.getTotalCostsAsync().thenApply(value -> {
            if (value != null) {
                System.out.println("app is finished " + Thread.currentThread().getName());
                return value;
            } else {
                System.out.println("app is not finished");
                return null;
            }
        });

    }

    @GetMapping("/total/costs/v4")
    //@SchemaMapping(typeName = "Query", fieldName = "bookById")
    public CompletableFuture<TotalCostResponse> totalCostsv4() {

        /**
         *
         */

        return productService.getTotalCostsAsyncWayForkJoin().thenApply(value -> {
            if (value != null) {
                System.out.println("app is finished " + Thread.currentThread().getName());
                return value;
            } else {
                System.out.println("app is not finished");
                return null;
            }
        });


    }
}
