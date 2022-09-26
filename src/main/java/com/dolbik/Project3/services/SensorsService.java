package com.dolbik.Project3.services;

import com.dolbik.Project3.models.Sensor;
import com.dolbik.Project3.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepository sensorsRepository;
    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }
    @Transactional
    public void register(Sensor sensor){
        sensorsRepository.save(sensor);
    }
    public Optional<Sensor> findByName(String name){
        return sensorsRepository.findByName(name);
    }
}
