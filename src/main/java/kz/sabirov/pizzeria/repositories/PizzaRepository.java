package kz.sabirov.pizzeria.repositories;

import jakarta.transaction.Transactional;
import kz.sabirov.pizzeria.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findAllById(Long id);
}
