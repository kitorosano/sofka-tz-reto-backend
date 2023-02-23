package uy.com.sofka.retodevjunior.dtos;

import uy.com.sofka.retodevjunior.models.Buy;

import java.util.List;

public class BuyListDTO {
  private List<BuyDTO> buys;
  private Integer currentPage;
  private Integer totalCount;
  
  public BuyListDTO() {}
  
  public BuyListDTO(List<BuyDTO> buys, Integer currentPage, Integer totalCount){
    this.buys = buys;
    this.currentPage = currentPage;
    this.totalCount = totalCount;
  }
  
  public List<BuyDTO> getBuys() {
    return buys;
  }
  
  public void setBuys(List<BuyDTO> buys) {
    this.buys = buys;
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
}
