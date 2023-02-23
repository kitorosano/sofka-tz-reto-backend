package uy.com.sofka.retodevjunior.mappers;

import uy.com.sofka.retodevjunior.dtos.BuyDTO;
import uy.com.sofka.retodevjunior.models.Buy;

import java.util.List;
import java.util.stream.Collectors;

public class BuyMapper {
  
  
  //  ======= DTO -> ENTITY ======== //
  public Buy fromDTO2Entity(BuyDTO dto) {
    Buy buy = new Buy();
    buy.setId(dto.getId());
    buy.setClientName(dto.getClientName());
    buy.setClientId(dto.getClientId());
    buy.setClientIdType(dto.getClientIdType());
    buy.setDate(dto.getDate());
    buy.setProducts(dto.getProducts());
    return buy;
  }
  
  public Buy fromDTO2Entity(BuyDTO dto, Buy buy) {
    buy.setId(dto.getId());
    buy.setClientName(dto.getClientName());
    buy.setClientId(dto.getClientId());
    buy.setClientIdType(dto.getClientIdType());
    buy.setDate(dto.getDate());
    buy.setProducts(dto.getProducts());
    return buy;
  }
  
  //  ======= ENTITY -> DTO ======== //
  public BuyDTO fromEntity2DTO(Buy entity) {
    BuyDTO buyDTO = new BuyDTO();
    buyDTO.setId(entity.getId());
    buyDTO.setClientName(entity.getClientName());
    buyDTO.setClientId(entity.getClientId());
    buyDTO.setClientIdType(entity.getClientIdType());
    buyDTO.setDate(entity.getDate());
    buyDTO.setProducts(entity.getProducts());
    return buyDTO;
  }
  
  public List<BuyDTO> fromListEntity2ListDTO(List<Buy> entity) {
    return entity.stream().map(this::fromEntity2DTO).collect(Collectors.toList());
  }
}
