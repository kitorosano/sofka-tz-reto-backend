package uy.com.sofka.retodevjunior.dtos;

import java.time.LocalDateTime;
import java.util.Map;

public class BuyDTO {
  private String id;
  private String clientName;
  private String clientIdType;
  private String clientId;
  private LocalDateTime date = LocalDateTime.now();
  private Map<String, Integer> products;
  
  public BuyDTO() {
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getClientName() {
    return clientName;
  }
  
  public void setClientName(String clientName) {
    if(clientName == null || clientName.isEmpty())
      throw new IllegalArgumentException("El clientName no puede ser vacio o nulo");
    this.clientName = clientName;
  }
  
  public String getClientIdType() {
    return clientIdType;
  }
  
  public void setClientIdType(String clientIdType) {
    if(clientIdType == null || clientIdType.isEmpty())
      throw new IllegalArgumentException("El clientIdType no puede ser vacio o nulo");
    this.clientIdType = clientIdType;
  }
  
  public String getClientId() {
    return clientId;
  }
  
  public void setClientId(String clientId) {
    if(clientId == null || clientId.isEmpty())
      throw new IllegalArgumentException("El clientId no puede ser vacio o nulo");
    this.clientId = clientId;
  }
  
  public LocalDateTime getDate() {
    return date;
  }
  
  public void setDate(LocalDateTime date) {
    this.date = date;
  }
  
  public Map<String, Integer> getProducts() {
    return products;
  }
  
  public void setProducts(Map<String, Integer> products) {
    this.products = products;
  }
}
