package by.innotechsolutions.practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "geolocation")
public class Geolocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double longitude;
    private Double latitude;
    private int userId;
    private boolean sos;
    private LocalTime time;
}
