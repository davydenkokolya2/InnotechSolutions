package by.innotechsolutions.practice.repository;

import by.innotechsolutions.practice.entity.GeolocationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationRepository extends JpaRepository<GeolocationDTO, Long> {
}
