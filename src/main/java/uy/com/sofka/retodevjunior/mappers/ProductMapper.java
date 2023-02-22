package uy.com.sofka.retodevjunior.mappers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.models.Product;

public class ProductMapper {
  
  
  //  ======= DTO -> ENTITY ======== //
  public Product fromDTO2Entity(ProductDTO dto) {
    Product producto = new Product();
    producto.setId(dto.getId());
    producto.setName(dto.getName());
    producto.setInventory(dto.getInventory());
    producto.setMin(dto.getMin());
    producto.setMax(dto.getMax());
    producto.setIsEnabled(dto.isEnabled());
    return producto;
  }
  
  public Product fromDTO2Entity(ProductDTO dto, Product producto) {
    producto.setId(dto.getId());
    producto.setName(dto.getName());
    producto.setInventory(dto.getInventory());
    producto.setMin(dto.getMin());
    producto.setMax(dto.getMax());
    producto.setIsEnabled(dto.isEnabled());
    return producto;
  }
  
  public Mono<Product> fromMonoDTO2MonoEntity(Mono<ProductDTO> dto) {
    return dto.map(productoDTO -> {
      Product producto = new Product();
      producto.setId(productoDTO.getId());
      producto.setName(productoDTO.getName());
      producto.setInventory(productoDTO.getInventory());
      producto.setMin(productoDTO.getMin());
      producto.setMax(productoDTO.getMax());
      producto.setIsEnabled(productoDTO.isEnabled());
      return producto;
    });
  }
  
  public Mono<Product> fromMonoDTO2MonoEntity(Mono<ProductDTO> dto, Product producto) {
    return dto.map(productoDTO -> {
      producto.setId(productoDTO.getId());
      producto.setName(productoDTO.getName());
      producto.setInventory(productoDTO.getInventory());
      producto.setMin(productoDTO.getMin());
      producto.setMax(productoDTO.getMax());
      producto.setIsEnabled(productoDTO.isEnabled());
      return producto;
    });
  }
  
  //  ======= ENTITY -> DTO ======== //
  public ProductDTO fromEntity2DTO(Product entity) {
    ProductDTO productoDTO = new ProductDTO();
    productoDTO.setId(entity.getId());
    productoDTO.setName(entity.getName());
    productoDTO.setInventory(entity.getInventory());
    productoDTO.setMin(entity.getMin());
    productoDTO.setMax(entity.getMax());
    productoDTO.setIsEnabled(entity.isEnabled());
    return productoDTO;
  }
  
  public Mono<ProductDTO> fromMonoEntity2MonoDTO(Mono<Product> entity) {
    return entity.map(producto -> {
      ProductDTO dto = new ProductDTO();
      dto.setId(producto.getId());
      dto.setName(producto.getName());
      dto.setInventory(producto.getInventory());
      dto.setMin(producto.getMin());
      dto.setMax(producto.getMax());
      dto.setIsEnabled(producto.isEnabled());
      return dto;
    });
  }
  
  public Flux<ProductDTO> fromFluxEntity2FluxDTO(Flux<Product> entity) {
    return entity.map(producto -> {
      ProductDTO dto = new ProductDTO();
      dto.setId(producto.getId());
      dto.setName(producto.getName());
      dto.setInventory(producto.getInventory());
      dto.setMin(producto.getMin());
      dto.setMax(producto.getMax());
      dto.setIsEnabled(producto.isEnabled());
      return dto;
    });
  }
}
