package uy.com.sofka.retodevjunior.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uy.com.sofka.retodevjunior.dtos.BoughtProductDTO;
import uy.com.sofka.retodevjunior.dtos.BuyDTO;
import uy.com.sofka.retodevjunior.dtos.BuyListDTO;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.mappers.BuyMapper;
import uy.com.sofka.retodevjunior.mappers.ProductMapper;
import uy.com.sofka.retodevjunior.models.Product;
import uy.com.sofka.retodevjunior.repositories.IBuyRepository;
import uy.com.sofka.retodevjunior.repositories.IProductRepository;
import uy.com.sofka.retodevjunior.services.IBuyService;

import java.util.Map;
import java.util.Optional;

@Service
public class BuyServiceImpl implements IBuyService {
  
  @Autowired
  private IBuyRepository buyRepository;
  @Autowired
  private IProductRepository productRepository;
  BuyMapper buyMapper = new BuyMapper();
  ProductMapper productMapper = new ProductMapper();
  
  @Override
  public BuyDTO save(BuyDTO buyDTO) {
    buyDTO.getProducts().forEach(boughtProduct -> {
      var productId = boughtProduct.getId();
      var quantity = boughtProduct.getQuantity();
      Optional<Product> product = productRepository.findById(productId);
      if(product.isPresent()) {
        ProductDTO productDTO = productMapper.fromEntity2DTO(product.get());
        try {
          productDTO.removeInventory(quantity);
          // if there is no error then add it again so we can test the other products inventories
          productDTO.addInventory(quantity);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException(productDTO.getName() + e.getMessage());
        }
      } else {
        throw new IllegalArgumentException("Product not found");
      }
    });
    // Now we can save the removes from the inventories because we know that there is the required stock
    buyDTO.getProducts().forEach(boughtProduct -> {
      var productId = boughtProduct.getId();
      var quantity = boughtProduct.getQuantity();
      
      Optional<Product> product = productRepository.findById(productId);
      if(product.isPresent()) {
        ProductDTO productDTO = productMapper.fromEntity2DTO(product.get());
        productDTO.removeInventory(quantity);
        productRepository.save(productMapper.fromDTO2Entity(productDTO));
      }
    });
    
    return buyMapper.fromEntity2DTO(
        buyRepository.save(
            buyMapper.fromDTO2Entity(buyDTO)
        )
    );
  }
  
  @Override
  public BuyListDTO findAll(Pageable pageable) {
    return new BuyListDTO(
        buyMapper.fromListEntity2ListDTO(
            buyRepository.findAll(pageable).getContent()),
        pageable.getPageNumber(),
        (int) buyRepository.count()
    );
  }
}
