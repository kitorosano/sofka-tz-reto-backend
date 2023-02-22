package uy.com.sofka.retodevjunior.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;

public interface IProductService {
  Mono<ProductDTO> save(ProductDTO dto);
  Flux<ProductDTO> findAll();
}
