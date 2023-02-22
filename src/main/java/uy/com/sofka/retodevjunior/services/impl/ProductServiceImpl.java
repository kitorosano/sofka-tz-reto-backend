package uy.com.sofka.retodevjunior.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
  public Mono<ProductDTO> save(ProductDTO productDTO) {
    return mapper.fromMonoEntity2MonoDTO(repository.save(
        mapper.fromDTO2Entity(productDTO))
    );
  } // DTO -> ENTITY -> |SAVE| -> MONO<ENTITY> -> MONO<DTO> -> |RETURN|
  
  @Override
  public Flux<ProductDTO> findAll() {
    return mapper.fromFluxEntity2FluxDTO(repository.findAll()
        .buffer(100)
        .flatMap(product ->
            Flux.fromStream(product.parallelStream())
        ));
  }
  
  @Override
  public Mono<ProductDTO> findById(String id) {
    return mapper.fromMonoEntity2MonoDTO(repository.findById(id));
  }
  
  @Override
  public Mono<ProductDTO> update(String id, ProductDTO productDTO) {
    return mapper.fromMonoEntity2MonoDTO(repository.findById(id)
        .flatMap(product ->
            repository.save(mapper.fromDTO2Entity(productDTO, product))
        ));
  }
  
}
