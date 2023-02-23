package uy.com.sofka.retodevjunior.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document(collection = "buys")
public class Buy {
  @Id
  private String id = UUID.randomUUID().toString().substring(0, 10);
  private String clientName;
  private String clientIdType;
  private String clientId;
  private LocalDateTime date = LocalDateTime.now();
  private Map<String, Integer> products;
  
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
    this.clientName = clientName;
  }
  
  public String getClientIdType() {
    return clientIdType;
  }
  
  public void setClientIdType(String clientIdType) {
    this.clientIdType = clientIdType;
  }
  
  public String getClientId() {
    return clientId;
  }
  
  public void setClientId(String clientId) {
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
  
  @Override
  public String toString() {
    return "Buys{" +
        "id='" + id + '\'' +
        ", clientName='" + clientName + '\'' +
        ", clientIdType='" + clientIdType + '\'' +
        ", clientId='" + clientId + '\'' +
        ", date=" + date +
        ", products=" + products +
        '}';
  }
}
