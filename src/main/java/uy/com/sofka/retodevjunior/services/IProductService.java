package uy.com.sofka.retodevjunior.services;

import reactor.core.publisher.Flux;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;

public interface IProductService {
  Flux<ProductDTO> findAll();
}
