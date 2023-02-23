package uy.com.sofka.retodevjunior.dtos;

public class ProductDTO {
  private String id;
  private String name;
  private Integer inventory;
  private Integer min = 0;
  private Integer max = Integer.MAX_VALUE;
  private Boolean enabled;

  public ProductDTO() {}


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
  
  // ====== SETTERS VALIDADOS ====== //

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    // verificar que no sea null o vacio
    if (name == null || name.isEmpty())
      throw new IllegalArgumentException("Name must not be null or empty");
    this.name = name;
  }
  public void setInventory(Integer inventory) {
    // verificar que no sea null
    if (inventory == null)
      throw new IllegalArgumentException("Inventory must not be null");
    // verificar que no sea menor a min o mayor a max
    if (inventory < this.min)
      throw new IllegalArgumentException("Inventory must no be less than min");
    if (inventory > this.max)
      throw new IllegalArgumentException("Inventory must no be greater than max");
    this.inventory = inventory;
  }
  
  public void addInventory(Integer quantity) {
    // verificar que no sea null
    if (quantity == null)
      throw new IllegalArgumentException(" quantity must not be null");
    // verificar que no sea menor a min o mayor a max
    if (this.inventory + quantity < this.min)
      throw new IllegalArgumentException(" quantity must no be less than current inventory and shouldn't let the inventory go below the minimum");
    if (this.inventory + quantity > this.max)
      throw new IllegalArgumentException(" quantity must no be greater than current inventory and shouldn't let the inventory go above the maximum");
    this.inventory += quantity;
  }
  
  public void removeInventory(Integer quantity) {
    // verificar que no sea null
    if (quantity == null)
      throw new IllegalArgumentException(" quantity must not be null");
    // verificar que no sea menor a min o mayor a max
    if (this.inventory - quantity < this.min)
      throw new IllegalArgumentException(" quantity must no be less than current inventory and shouldn't let the inventory go below the minimum");
    if (this.inventory - quantity > this.max)
      throw new IllegalArgumentException(" quantity must no be greater than current inventory and shouldn't let the inventory go above the maximum");
    this.inventory -= quantity;
  }
  
  public void toggleEnabled() {
    this.enabled = !this.enabled;
  }
  
  public void setMin(Integer min) {
    // verificar que si es nulo o negativo que sea 0
    if (min == null || min < 0)
      this.min = 0;
    this.min = min;
  }
  public void setMax(Integer max) {
    // verificiar que si es nulo o menor que min que sea igual a min sea infinito
    if (max == null || max < this.min)
      this.max = Integer.MAX_VALUE;
    this.max = max;
  }
  public void setIsEnabled(Boolean enabled) {
    this.enabled = enabled;
  }


}
