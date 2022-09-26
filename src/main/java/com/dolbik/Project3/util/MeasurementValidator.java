package com.dolbik.Project3.util;

import com.dolbik.Project3.models.Measurement;
import com.dolbik.Project3.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorsService sensorsService;
    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getSensor() == null){
            errors.rejectValue("sensor","","Sensor should be not empty");
        } else {
            String sensorName = measurement.getSensor().getName();
            if (sensorName == null){
                errors.rejectValue("sensor","", "Name of sensor should not be empty");
            } else {
                if (sensorsService.findByName(sensorName).isEmpty()){
                    errors.rejectValue("sensor","","There's no sensor with this name in the database");
                }
            }
        }

    }
}
