package kz.yeltayev.aqm.controller;

import kz.yeltayev.aqm.exception.ResourceNotFoundException;
import kz.yeltayev.aqm.model.dto.PressureDto;
import kz.yeltayev.aqm.model.dto.TemperatureDto;
import kz.yeltayev.aqm.model.entity.Pressure;
import kz.yeltayev.aqm.model.request.PressureRequest;
import kz.yeltayev.aqm.model.request.TemperatureRequest;
import kz.yeltayev.aqm.service.PressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PressureController {

    private PressureService pressureService;

    @Autowired
    public PressureController(PressureService pressureService) {
        this.pressureService = pressureService;
    }

    @GetMapping("/pressures")
    public List<PressureDto> fetchPressures() {
        return pressureService.fetchPressures();
    }

    @GetMapping("/pressures/{placeId}")
    public List<PressureDto> fetchPressuresByPlace(@PathVariable(value = "placeId") Long placeId) {
        List<PressureDto> pressureDtoList = pressureService.fetchPressuresByPlace(placeId);
        return pressureDtoList;
    }

    @PostMapping("/pressures")
    public PressureDto addPressure(@Valid @RequestBody PressureRequest pressureRequest) throws ResourceNotFoundException {
        return pressureService.addPressure(pressureRequest);
    }
}
