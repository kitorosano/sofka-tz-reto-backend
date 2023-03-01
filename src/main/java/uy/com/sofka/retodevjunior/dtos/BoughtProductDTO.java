package uy.com.sofka.retodevjunior.dtos;

public class BoughtProductDTO {
  private String id;
  private String name;
  private Integer inventory;
  private Integer min = 0;
  private Integer max = Integer.MAX_VALUE;
  private Boolean enabled;
  private Integer quantity;
  
  public BoughtProductDTO() {
  }
  
  public BoughtProductDTO(ProductDTO productDTO, Integer quantity) {
    this.id = productDTO.getId();
    this.name = productDTO.getName();
    this.inventory = productDTO.getInventory();
    this.min = productDTO.getMin();
    this.max = productDTO.getMax();
    this.enabled = productDTO.isEnabled();
    this.quantity = quantity;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Integer getInventory() {
    return this.inventory;
  }
  
  public Integer getMin() {
    return this.min;
  }
  
  public Integer getMax() {
    return this.max;
  }
  
  public Boolean isEnabled() {
    return this.enabled;
  }
  public Integer getQuantity() {
    return this.quantity;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setInventory(Integer inventory) {
    this.inventory = inventory;
  }
  
  public void setMin(Integer min) {
    this.min = min;
  }
  
  public void setMax(Integer max) {
    this.max = max;
  }
  
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
