package com.dolbik.Project3.controllers;

import com.dolbik.Project3.dto.MeasurementDTO;
import com.dolbik.Project3.models.Measurement;
import com.dolbik.Project3.models.MeasurementsResponse;
import com.dolbik.Project3.services.MeasurementsService;
import com.dolbik.Project3.util.MeasurementErrorResponse;
import com.dolbik.Project3.util.MeasurementException;
import com.dolbik.Project3.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

import static com.dolbik.Project3.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(ModelMapper modelMapper,
                                  MeasurementValidator measurementValidator,
                                  MeasurementsService measurementsService) {
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.measurementsService = measurementsService;
    }

    @GetMapping()
    public MeasurementsResponse getMeasurements() {
        return new MeasurementsResponse(measurementsService.findAll()
                .stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Integer getCountOfRainingDays() {
        return measurementsService.getCountOfRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        measurementsService.addMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(@NotNull final MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
