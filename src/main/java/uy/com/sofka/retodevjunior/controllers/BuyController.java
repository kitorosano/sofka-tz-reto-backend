package uy.com.sofka.retodevjunior.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.com.sofka.retodevjunior.dtos.BuyDTO;
import uy.com.sofka.retodevjunior.dtos.BuyListDTO;
import uy.com.sofka.retodevjunior.services.IBuyService;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/buys")
public class BuyController {
  @Autowired
  private IBuyService service;
  
  @PostMapping("")
  @ApiOperation(value = "Create a buy", notes = "Create a buy")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> create(@RequestBody BuyDTO dto) {
    try {
      System.out.println("BuyController.create");
      BuyDTO savedDTO = service.save(dto);
      return new ResponseEntity<BuyDTO>(savedDTO, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
  }
  
  // ============ GET ===================
  @GetMapping("")
  @ApiOperation(value = "Find all buys", notes = "Return all buys")
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
      BuyListDTO buyList = service.findAll(pageable);
      return new ResponseEntity<BuyListDTO>(buyList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
