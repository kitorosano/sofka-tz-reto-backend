package uy.com.sofka.retodevjunior.mappers;

import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
  
  public Product fromEntityToUpdateEnabled(Product entity, Boolean enabled) {
    entity.setIsEnabled(enabled);
    return entity;
  }
  
  public List<ProductDTO> fromListEntity2ListDTO(List<Product> entity) {
    return entity.stream().map(this::fromEntity2DTO).collect(Collectors.toList());
  }
}
