package uy.com.sofka.retodevjunior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uy.com.sofka.retodevjunior.dtos.BoughtProductDTO;
import uy.com.sofka.retodevjunior.dtos.BuyDTO;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.services.IBuyService;
import uy.com.sofka.retodevjunior.services.IProductService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

@SpringBootTest
public class BuyServiceTest {
  
  @Autowired
  private IBuyService buyService;
  @Autowired
  private IProductService productService;
  //Test para crear un buy
  @Test
  void createBuy() throws Exception {
    // Arrange
    ProductDTO product1 = new ProductDTO();
    product1.setId("x1");
    product1.setName("Product 1");
    product1.setInventory(10);
    product1.setMin(3);
    product1.setMax(10);
    product1.setIsEnabled(true);
    productService.save(product1);
    ProductDTO product2 = new ProductDTO();
    product2.setId("x2");
    product2.setName("Product 2");
    product2.setInventory(10);
    product2.setMin(3);
    product2.setMax(10);
    product2.setIsEnabled(true);
    productService.save(product2);
    
    
    
    BuyDTO buy = new BuyDTO();
    buy.setClientName("Client");
    buy.setClientId("123");
    buy.setClientIdType("CC");
    buy.setDate(LocalDateTime.now());
    buy.setProducts(
      List.of(
          new BoughtProductDTO(product1, 3),
          new BoughtProductDTO(product2, 2)
      )
    );
    ////Act
    BuyDTO result = buyService.save(buy);
    //
    // Assert
    Assertions.assertEquals("Client", result.getClientName());
    Assertions.assertEquals("123", result.getClientId());
    Assertions.assertEquals("CC", result.getClientIdType());
    Assertions.assertEquals(2, result.getProducts().size());
  }
  
  // Test para listar los buy
  @Test
  void findAllBuy() throws Exception {
    //Arrange
    Integer count = 1;
    Pageable pageable = PageRequest.of(0,1);
    //Act
    var result = buyService.findAll(pageable);
    //Assert
    Assertions.assertEquals(count, result.getBuys().size());
  }
}
