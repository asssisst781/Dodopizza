package kz.sabirov.pizzeria.security;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findAll();
}
