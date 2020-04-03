package kz.yeltayev.aqm.controller;

import kz.yeltayev.aqm.exception.ResourceNotFoundException;
import kz.yeltayev.aqm.model.dto.WeatherDto;
import kz.yeltayev.aqm.model.request.WeatherRequest;
import kz.yeltayev.aqm.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weathers")
    public List<WeatherDto> fetchWeathers() {
        return weatherService.fetchWeathers();
    }

    @GetMapping("/weathers/{placeId}")
    public List<WeatherDto> fetchWeathersByPlace(@PathVariable(value = "placeId") Long placeId) {
        List<WeatherDto> weatherDtoList = weatherService.fetchWeathersByPlace(placeId);
        return weatherDtoList;
    }

    @PostMapping("/weathers")
    public WeatherDto addWeather(@Valid @RequestBody WeatherRequest weatherRequest) throws ResourceNotFoundException {
        return weatherService.addWeather(weatherRequest);
    }
}
