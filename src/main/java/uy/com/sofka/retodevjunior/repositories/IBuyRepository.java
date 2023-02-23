package uy.com.sofka.retodevjunior.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uy.com.sofka.retodevjunior.models.Buy;

@Repository
public interface IBuyRepository extends MongoRepository<Buy, String> {
}
