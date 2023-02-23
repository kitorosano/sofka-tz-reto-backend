package uy.com.sofka.retodevjunior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uy.com.sofka.retodevjunior.dtos.ProductDTO;
import uy.com.sofka.retodevjunior.dtos.ProductListDTO;
import uy.com.sofka.retodevjunior.services.IProductService;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ProductServiceTest {
  @Autowired
  private IProductService service;
  //Test para crear un product
  @Test
  void createProduct() throws Exception {
    //
    // Arrange
    ProductDTO product = new ProductDTO();
    product.setName("Product 1");
    product.setInventory(10);
    product.setMin(3);
    product.setMax(10);
    product.setIsEnabled(true);
    ////Act
    ProductDTO result = service.save(product);
    
    //
    // Assert
    Assertions.assertEquals("Product 1", result.getName());
    Assertions.assertEquals(10, result.getInventory());
    Assertions.assertEquals(3, result.getMin());
    Assertions.assertEquals(10, result.getMax());
    Assertions.assertEquals(true, result.isEnabled());
  }
  
  //Test para listar los products
  @Test
  void findAllProducts() throws Exception {
    //Arrange
    Integer count = 3;
    //Act
    Pageable pageable = PageRequest.of(0, 10);
    ProductListDTO result = service.findAll(pageable);
    
    //Assert
    Assertions.assertEquals(count, result.getTotalCount());
  }
  
  //Test para listar product por su id
  @Test
  void findByIdProduct() throws Exception {
    //Arrange
    ProductDTO product = new ProductDTO();
    String id = UUID.randomUUID().toString().substring(0, 10);
    product.setId(id);
    product.setName("Product 2");
    product.setInventory(10);
    product.setMin(3);
    product.setMax(10);
    product.setIsEnabled(true);
    service.save(product);

    //Act
    ProductDTO result = service.findById(id).get();
    
    //Assert
    Assertions.assertEquals("Product 2", result.getName());
    Assertions.assertEquals(10, result.getInventory());
    Assertions.assertEquals(3, result.getMin());
    Assertions.assertEquals(10, result.getMax());
    Assertions.assertEquals(true, result.isEnabled());
  }
  //Test para eliminar los products
  @Test
  void deleteAllProducts() throws Exception {
    //Act
    service.deleteAll();
    //Assert
    Pageable pageable = PageRequest.of(0, 10);
    ProductListDTO result = service.findAll(pageable);
    Assertions.assertEquals(0, result.getTotalCount());
  }
  //Test para eliminar un product por su id
  @Test
  void createAndDeleteProduct() throws Exception {
    //Arrange
    ProductDTO product1 = new ProductDTO();
    String id = UUID.randomUUID().toString().substring(0, 10);
    product1.setId(id);
    product1.setName("Product 3");
    product1.setInventory(5);
    product1.setMin(3);
    product1.setMax(10);
    product1.setIsEnabled(true);
    service.save(product1);
    //Act
    service.delete(id);
    
    //Assert
    Optional<ProductDTO> result = service.findById(id);
    Assertions.assertFalse(result.isPresent());
  }
  
  @Test
  void deleteByIdProduct() throws Exception {
    //Arrange
    String id = "xxxxxxx";
    //Act
    service.delete(id);
    //Assert
    Optional<ProductDTO> result = service.findById(id);
    Assertions.assertFalse(result.isPresent());
  }
  //Test para actualizar un product por id
  @Test
  void createAndUpdateByIdProduct() throws Exception {
    //Arrange
    ProductDTO product1 = new ProductDTO();
    product1.setName("Product 4");
    product1.setInventory(5);
    product1.setMin(3);
    product1.setMax(10);
    product1.setIsEnabled(true);
    String id = service.save(product1).getId();
  
    //Act
    product1.setName("Product 4 modificado");
    product1.setInventory(9);
    product1.setMin(5);
    product1.setMax(15);
    product1.setIsEnabled(false);
    service.update(id, product1);
  
    //Assert
    Assertions.assertEquals("Product 4 modificado", product1.getName());
    Assertions.assertEquals(9, product1.getInventory());
    Assertions.assertEquals(5, product1.getMin());
    Assertions.assertEquals(15, product1.getMax());
    Assertions.assertEquals(false, product1.isEnabled());
  }
  
  // quitar 2 item del inventario
  @Test
  void removeFromInventory() throws Exception {
    //Arrange
    ProductDTO product1 = new ProductDTO();
    product1.setName("Product 5");
    product1.setInventory(5);
    product1.setMin(3);
    product1.setMax(10);
    product1.setIsEnabled(true);
    String id = service.save(product1).getId();
  
    //Act
    var result = service.removeFromInventory(id, 2);
  
    //Assert
    Assertions.assertEquals(3, result.getInventory());
  }
  
  // agregar 2 item al inventario
  @Test
  void addToInventory() throws Exception {
    //Arrange
    ProductDTO product1 = new ProductDTO();
    product1.setName("Product 6");
    product1.setInventory(5);
    product1.setMin(3);
    product1.setMax(10);
    product1.setIsEnabled(true);
    String id = service.save(product1).getId();
  
    //Act
    var result = service.addToInventory(id, 2);
  
    //Assert
    Assertions.assertEquals(7, result.getInventory());
  }
  
  // cambiar estado producto
  @Test
  void changeStatus() throws Exception {
    //Arrange
    ProductDTO product1 = new ProductDTO();
    product1.setName("Product 7");
    product1.setInventory(5);
    product1.setMin(3);
    product1.setMax(10);
    product1.setIsEnabled(true);
    String id = service.save(product1).getId();
  
    //Act
    var result = service.toggleEnabled(id, false);
  
    //Assert
    Assertions.assertEquals(false, result.isEnabled());
  }
}
