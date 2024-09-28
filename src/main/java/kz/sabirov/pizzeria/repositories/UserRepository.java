package kz.sabirov.pizzeria.repositories;

import jakarta.transaction.Transactional;
import kz.sabirov.pizzeria.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findAllByEmail(String email);
}
