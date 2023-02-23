package uy.com.sofka.retodevjunior.dtos;

import java.util.List;

public class ProductListDTO {
  
  private List<ProductDTO> products;
  private Integer currentPage;
  private Integer totalCount;
  
  public ProductListDTO() {
  }
  
  public ProductListDTO(List<ProductDTO> products, Integer currentPage, Integer totalCount) {
    this.products = products;
    this.currentPage = currentPage;
    this.totalCount = totalCount;
  }
  
  public List<ProductDTO> getProducts() {
    return products;
  }
  
  public void setProducts(List<ProductDTO> products) {
    this.products = products;
  }
  
  public Integer getCurrentPage() {
    return currentPage;
  }
  
  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }
  
  public Integer getTotalCount() {
    return totalCount;
  }
  
  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }
  
  @Override
  public String toString() {
    return "ProductListDTO{" +
        "products=" + products +
        ", currentPage=" + currentPage +
        ", totalCount=" + totalCount +
        '}';
  }
}
