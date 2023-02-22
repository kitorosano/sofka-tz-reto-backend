package uy.com.sofka.retodevjunior.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.services.IProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
  
  @Autowired
  private IProductService service;
  
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
  
}
