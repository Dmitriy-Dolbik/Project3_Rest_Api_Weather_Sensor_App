package com.dolbik.Project3.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="Measurement")
public class Measurement {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="value")
    @NotNull(message = "Value should be not empty")
    @Min(value = -100, message = "Value should be between -100 and 100 degrees")
    @Max(value = 100, message = "Value should be between -100 and 100 degrees")
    private Double value;

    @Column(name="raining")
    @NotNull
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String raining;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement(){}

    public Measurement(Integer id, Double value, String raining, LocalDateTime createdAt) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                '}';
    }
}
