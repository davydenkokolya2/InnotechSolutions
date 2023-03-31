package by.innotechsolutions.practice.repository;

import by.innotechsolutions.practice.entity.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {

    List<Geolocation> findByTime();
}
