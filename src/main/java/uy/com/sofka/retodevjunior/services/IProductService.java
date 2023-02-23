package uy.com.sofka.retodevjunior.services;

import org.springframework.data.domain.Pageable;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.dtos.ProductListDTO;
import java.util.Optional;

public interface IProductService {
  ProductDTO save(ProductDTO dto);
  ProductListDTO findAll(Pageable pageable);
  Optional<ProductDTO> findById(String id);
  ProductDTO update(String id, ProductDTO dto);
  ProductDTO removeFromInventory(String id, Integer quantity);
  ProductDTO addToInventory(String id, Integer quantity);
  ProductDTO toggleEnabled(String id, Boolean enabled);
  void delete(String id);
  void deleteAll();
}
