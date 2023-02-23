package uy.com.sofka.retodevjunior.services;

import org.springframework.data.domain.Pageable;
import uy.com.sofka.retodevjunior.dtos.BuyDTO;
import uy.com.sofka.retodevjunior.dtos.BuyListDTO;


public interface IBuyService {
  BuyDTO save(BuyDTO dto);
  BuyListDTO findAll(Pageable pageable);
}
