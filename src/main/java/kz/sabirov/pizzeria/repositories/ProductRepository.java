package kz.sabirov.pizzeria.repositories;

import jakarta.transaction.Transactional;
import kz.sabirov.pizzeria.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository <Product,Long>{
    Product findAllById(Long id);
}
