package com.dolbik.Project3.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class MeasurementDTO {

    @NotNull(message = "Value should be not empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be lower than 100")
    private Double value;

    @NotNull
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String raining;

    private SensorDTO sensor;

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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
