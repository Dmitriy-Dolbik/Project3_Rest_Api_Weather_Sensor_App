package com.dolbik.Project3.services;

import com.dolbik.Project3.models.Measurement;
import com.dolbik.Project3.models.Sensor;
import com.dolbik.Project3.repositories.MeasurementsRepository;
import com.dolbik.Project3.util.MeasurementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;
    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }
    @Transactional
    public void addMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }
    private void enrichMeasurement(Measurement measurement){
        Sensor sensor = sensorsService.findByName(measurement.getSensor().getName()).orElse(null);
        if (sensor == null){
            throw new MeasurementException("Sensor not found with name: "+measurement.getSensor().getName());
        }
        measurement.setSensor(sensor);

        measurement.setCreatedAt(LocalDateTime.now());
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }
    public int getCountOfRainyDays(){
        int count = 0;
        List<Measurement> measurements = measurementsRepository.findAll();
        for (Measurement measurement : measurements){
            if (Boolean.valueOf(measurement.getRaining())==true){
                count++;
            }
        }
        return count;
    }
}
