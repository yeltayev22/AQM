package kz.yeltayev.aqm.controller;

import kz.yeltayev.aqm.exception.ResourceNotFoundException;
import kz.yeltayev.aqm.model.dto.TemperatureDto;
import kz.yeltayev.aqm.model.entity.Temperature;
import kz.yeltayev.aqm.model.request.TemperatureRequest;
import kz.yeltayev.aqm.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TemperatureController {

    private TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/temperatures")
    public List<TemperatureDto> fetchTemperatures() {
        return temperatureService.fetchTemperatures();
    }

    @GetMapping("/temperatures/{placeId}")
    public List<TemperatureDto> fetchTemperaturesByPlace(@PathVariable(value = "placeId") Long placeId) {
        List<TemperatureDto> temperatureDtoList = temperatureService.fetchTemperaturesByPlace(placeId);
        return temperatureDtoList;
    }

    @PostMapping("/temperatures")
    public TemperatureDto addTemperature(@Valid @RequestBody TemperatureRequest temperatureRequest) throws ResourceNotFoundException {
        return temperatureService.addTemperature(temperatureRequest);
    }
}
