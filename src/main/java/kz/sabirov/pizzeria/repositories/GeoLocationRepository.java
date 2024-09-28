package kz.sabirov.pizzeria.repositories;

import jakarta.transaction.Transactional;
import kz.sabirov.pizzeria.entities.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface GeoLocationRepository extends JpaRepository<GeoLocation, Long> {
}
