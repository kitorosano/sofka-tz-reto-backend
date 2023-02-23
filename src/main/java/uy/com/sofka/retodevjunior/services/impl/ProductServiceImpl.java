package uy.com.sofka.retodevjunior.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.dtos.ProductListDTO;
import uy.com.sofka.retodevjunior.mappers.ProductMapper;
import uy.com.sofka.retodevjunior.models.Product;
import uy.com.sofka.retodevjunior.repositories.IProductRepository;
import uy.com.sofka.retodevjunior.services.IProductService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
  
  @Autowired
  private IProductRepository repository;
  ProductMapper mapper = new ProductMapper();
  
  @Override
  public ProductDTO save(ProductDTO productDTO) {
    return mapper.fromEntity2DTO(repository.save(
        mapper.fromDTO2Entity(productDTO))
    );
  } // DTO -> ENTITY -> |SAVE| -> ENTITY -> DTO -> |RETURN|
  
  @Override
  public ProductListDTO findAll(Pageable pageable) {
    return new ProductListDTO(
        mapper.fromListEntity2ListDTO(
            repository.findAll(pageable).getContent()),
        pageable.getPageNumber(),
        (int) repository.count()
    );
  }
  
  @Override
  public Optional<ProductDTO> findById(String id) {
    return repository
        .findById(id)
        .map(mapper::fromEntity2DTO);
  }
  
  @Override
  public ProductDTO update(String id, ProductDTO productDTO) {
    return repository.findById(id)
        .map(product -> mapper.fromDTO2Entity(productDTO, product))
        .map(repository::save)
        .map(mapper::fromEntity2DTO)
        .orElseThrow();
  }
  
  @Override
  public ProductDTO removeFromInventory(String id, Integer quantity){
    Optional<Product> product = repository.findById(id);
    if (product.isPresent()) {
      ProductDTO productDTO = mapper.fromEntity2DTO(product.get());
      productDTO.removeInventory(quantity);
      repository.save(mapper.fromDTO2Entity(productDTO));
      return productDTO;
    } else {
      throw new NoSuchElementException();
    }
  }
  @Override
  public ProductDTO addToInventory(String id, Integer quantity){
    Optional<Product> product = repository.findById(id);
    if (product.isPresent()) {
      ProductDTO productDTO = mapper.fromEntity2DTO(product.get());
      productDTO.addInventory(quantity);
      repository.save(mapper.fromDTO2Entity(productDTO));
      return productDTO;
    } else {
      throw new NoSuchElementException();
    }
  }
  
  @Override
  public ProductDTO toggleEnabled(String id, Boolean enabled){
      Optional<Product> product = repository.findById(id);
      if (product.isPresent()) {
        ProductDTO productDTO = mapper.fromEntity2DTO(product.get());
        productDTO.setIsEnabled(enabled);
        repository.save(mapper.fromDTO2Entity(productDTO));
        return productDTO;
      } else {
        throw new NoSuchElementException();
      }
  }
  
  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }
  
  @Override
  public void deleteAll() {
    repository.deleteAll();
  }
}
