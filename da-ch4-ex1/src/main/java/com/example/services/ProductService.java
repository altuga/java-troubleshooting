package com.example.services;

import com.example.model.dtos.TotalCostResponse;
import com.example.model.entities.Product;
import com.example.repositories.ProductRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  static ExecutorService executorService = Executors.newFixedThreadPool(15);
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }


  public  CompletableFuture<TotalCostResponse> getTotalCostsAsyncWay()  {
    return
            CompletableFuture.supplyAsync(() -> getTotalCosts(), executorService);

  }

  public  CompletableFuture<TotalCostResponse> getTotalCostsAsyncWayForkJoin()  {
    return
            CompletableFuture.supplyAsync(() -> getTotalCosts());

  }


  public TotalCostResponse getTotalCosts() {
    TotalCostResponse response = new TotalCostResponse();
    try {
      var products = productRepository.findAll();

      var costs = products.stream()
          .collect(Collectors.toMap(
              Product::getName,
              p -> p.getPrice().multiply(new BigDecimal(p.getQuantity()))));

      Thread.sleep(ThreadLocalRandom.current().nextInt(1000,5000));
      //Thread.currentThread().wait();
      response.setTotalCosts(costs);
      System.out.println(" Thread name " + Thread.currentThread().getName());
    } catch (Exception e) {
      e.printStackTrace(); // do nothing
    }

    return response;
  }

  @Async
  public CompletableFuture<TotalCostResponse> getTotalCostsAsync() {
    TotalCostResponse response = new TotalCostResponse();
    try {
      var products = productRepository.findAll();

      var costs = products.stream()
              .collect(Collectors.toMap(
                      Product::getName,
                      p -> p.getPrice().multiply(new BigDecimal(p.getQuantity()))));

      Thread.sleep(ThreadLocalRandom.current().nextInt(1000,5000));
      //Thread.currentThread().wait();
      response.setTotalCosts(costs);
      System.out.println(" Thread name " + Thread.currentThread().getName());
    } catch (Exception e) {
      e.printStackTrace(); // do nothing
    }

    return  CompletableFuture.completedFuture(response);
  }

}
