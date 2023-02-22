package uy.com.sofka.retodevjunior.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import uy.com.sofka.retodevjunior.models.Product;

public interface IProductRepository extends ReactiveMongoRepository<Product, String> {

}