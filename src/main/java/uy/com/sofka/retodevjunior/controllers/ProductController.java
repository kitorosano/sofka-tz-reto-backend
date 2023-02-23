package uy.com.sofka.retodevjunior.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.dtos.ProductListDTO;
import uy.com.sofka.retodevjunior.services.IProductService;

import java.util.NoSuchElementException;
import java.util.Objects;

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
   @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
    try {
      ProductDTO savedDTO = service.save(dto);
      return new ResponseEntity<ProductDTO>(savedDTO, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  
  }
  
  // ============ GET ===================
  @GetMapping("")
  @ApiOperation(value = "Find all products", notes = "Return all products")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> findAll(@RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer size) {
    if(Objects.isNull(page) || Objects.isNull(size))
      return new ResponseEntity<String>("Page and size must be not null", HttpStatus.BAD_REQUEST);
    try {
      Pageable pageable = PageRequest.of(page, size);
      ProductListDTO productList = service.findAll(pageable);
      return new ResponseEntity<ProductListDTO>(productList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  
  @GetMapping("/product")
  @ApiOperation(value = "Find a product by id", notes = "Return a product by id")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 400, message = "Bad Request"),
   @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> findById(@RequestParam(required = false) String id) {
    System.out.println("id: " + id);
    if(id == null || id.isEmpty())
      return new ResponseEntity<String>("Id must be not null or empty", HttpStatus.BAD_REQUEST);
    try {
      var product = service.findById(id);
      if (product.isEmpty()) {
        return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<ProductDTO>(product.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  // ============ PUT ===================
  @PutMapping("/product")
  @ApiOperation(value = "Update a product", notes = "Update a product by id")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 404, message = "Not found"),
   @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> update(@RequestParam(required = false) String id, @RequestBody ProductDTO product) {
    if(id == null || id.isEmpty())
      return new ResponseEntity<String>("Id must be not null or empty", HttpStatus.BAD_REQUEST);
    try {
      var productUpdated = service.update(id, product);
      return new ResponseEntity<ProductDTO>(productUpdated, HttpStatus.OK);
    } catch(NoSuchElementException e) {
      return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/product/remove")
  @ApiOperation(value = "Remove from product inventory", notes = "Remove from inventory by id")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 404, message = "Not found"),
   @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> updateRemoveFromInventory(@RequestParam(required = false) String id, @RequestBody Integer quantity){
    if(id == null || id.isEmpty())
      return new ResponseEntity<String>("Id must be not null or empty", HttpStatus.BAD_REQUEST);
    try {
      var productUpdated = service.removeFromInventory(id, quantity);
      return new ResponseEntity<ProductDTO>(productUpdated, HttpStatus.OK);
    } catch(NoSuchElementException e) {
      return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/product/add")
  @ApiOperation(value = "Add to product inventory", notes = "Add to inventory by id")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> updateAddToInventory(@RequestParam(required = false) String id, @RequestBody Integer quantity){
    if(id == null || id.isEmpty())
      return new ResponseEntity<String>("Id must be not null or empty", HttpStatus.BAD_REQUEST);
    try {
      var productUpdated = service.addToInventory(id, quantity);
      return new ResponseEntity<ProductDTO>(productUpdated, HttpStatus.OK);
    } catch(NoSuchElementException e) {
      return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/product/toggle")
  @ApiOperation(value = "Add to product inventory", notes = "Add to inventory by id")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> updateToggleEnabled(@RequestParam(required = false) String id, @RequestBody Boolean enabled){
    if(id == null || id.isEmpty())
      return new ResponseEntity<String>("Id must be not null or empty", HttpStatus.BAD_REQUEST);
    try {
      var productUpdated = service.toggleEnabled(id, enabled);
      return new ResponseEntity<ProductDTO>(productUpdated, HttpStatus.OK);
    } catch(NoSuchElementException e) {
      return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  // ============ DELETE ===================
  @DeleteMapping("/product")
  @ApiOperation(value = "Delete a product", notes = "Delete a product by id")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 404, message = "Not found"),
   @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> delete(@RequestParam(required = false) String id) {
    if(Objects.isNull(id))
      return new ResponseEntity<String>("Id must be not null", HttpStatus.BAD_REQUEST);
    try {
      service.delete(id);
      return new ResponseEntity<String>("Product deleted", HttpStatus.OK);
    } catch(NoSuchElementException e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @DeleteMapping("")
  @ApiOperation(value = "Delete all products", notes = "Delete all products")
  @ApiResponses(value = {
   @ApiResponse(code = 200, message = "OK"),
   @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> deleteAll() {
    try {
      service.deleteAll();
      return new ResponseEntity<String>("All products deleted", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
}
