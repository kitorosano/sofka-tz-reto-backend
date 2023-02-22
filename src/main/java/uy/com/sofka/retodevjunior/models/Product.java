package uy.com.sofka.retodevjunior.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

  @Id
  private String id = UUID.randomUUID().toString().substring(0, 10);
  private String name;
  private Integer inventory;
  private Integer min;
  private Integer max;
  private Boolean enabled = true;
 
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
  public void setIsEnabled(Boolean enabled) {
    this.enabled = enabled;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", inventory='" + getInventory() + "'" +
      ", min='" + getMin() + "'" +
      ", max='" + getMax() + "'" +
      ", enabled='" + isEnabled() + "'" +
      "}";
  }
  
}
