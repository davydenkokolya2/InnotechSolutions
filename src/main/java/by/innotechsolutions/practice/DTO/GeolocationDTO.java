package by.innotechsolutions.practice.DTO;

import jakarta.persistence.Id;

import java.time.LocalTime;

public class GeolocationDTO {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private Double longitude;

    private Double latitude;

    private int userId;

    private boolean sos;

    //@JsonFormat(pattern="HH:mm:ss")
    private LocalTime time;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getSos() {
        return sos;
    }

    public void setSos(boolean sos) {
        this.sos = sos;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
