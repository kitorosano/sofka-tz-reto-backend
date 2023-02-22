package uy.com.sofka.retodevjunior.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.mappers.ProductMapper;
import uy.com.sofka.retodevjunior.repositories.IProductRepository;
import uy.com.sofka.retodevjunior.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
  
  @Autowired
  private IProductRepository repository;
  
  ProductMapper mapper = new ProductMapper();
  
  @Override
  public Flux<ProductDTO> findAll() {
    return mapper.fromFluxEntity2FluxDTO(repository.findAll()
        .buffer(100)
        .flatMap(producto ->
            Flux.fromStream(producto.parallelStream())
        ));
  }
}
