package uy.com.sofka.retodevjunior.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.services.IProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
  
  @Autowired
  private IProductService service;
  
  @PostMapping("")
  @ApiOperation(value = "Create a product", notes = "Create a product")
  @ApiResponses(value = {
   @ApiResponse(code = 201, message = "Created"),
   @ApiResponse(code = 400, message = "Bad request")
  })
  public Mono<ProductDTO> create(@RequestBody ProductDTO product) {
    return service.save(product);
  }
  
  // ============ GET ===================
  @GetMapping("")
  @ApiOperation(value = "Find all products", notes = "Return all products")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 404, message = "Not found")
  })
  public Flux<ProductDTO> findAll() {
    return service.findAll();
  }
  
  @GetMapping("/{id}")
  @ApiOperation(value = "Find a product by id", notes = "Return a product by id")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 404, message = "Not found")
  })
  public Mono<ProductDTO> findById(@PathVariable("id") String id) {
    return service.findById(id);
  }
  
  // ============ PUT ===================
  @PutMapping("/{id}")
  @ApiOperation(value = "Update a product", notes = "Update a product by id")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 404, message = "Not found")
  })
  public Mono<ProductDTO> update(@PathVariable("id") String id, @RequestBody ProductDTO product) {
    return service.update(id, product);
  }
  
}
