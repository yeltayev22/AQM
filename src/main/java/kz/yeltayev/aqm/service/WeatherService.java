package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.exception.ResourceNotFoundException;
import kz.yeltayev.aqm.model.dto.WeatherDto;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.model.entity.Weather;
import kz.yeltayev.aqm.model.request.WeatherRequest;
import kz.yeltayev.aqm.repository.PlaceRepository;
import kz.yeltayev.aqm.repository.WeatherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private WeatherRepository weatherRepository;
    private PlaceRepository placeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository, PlaceRepository placeRepository) {
        this.weatherRepository = weatherRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional
    public WeatherDto addWeather(WeatherRequest weatherRequest) throws ResourceNotFoundException {
        Weather weather = new Weather();

        weather.setDateTime(weatherRequest.getDateTime());
        weather.setHumidity(weatherRequest.getHumidity());
        weather.setTemperature(weatherRequest.getTemperature());
        weather.setPressure(weatherRequest.getPressure());

        Place place = placeRepository.findById(weatherRequest.getPlaceId()).orElseThrow(
                () -> new ResourceNotFoundException("Place not found for this id : " + weatherRequest.getPlaceId()));

        weather.setPlace(place);

        return convertToDto(weatherRepository.save(weather));
    }

    @Transactional
    public List<WeatherDto> fetchWeathers() {
        List<Weather> weathers = weatherRepository.findAll();
        return convertToListWeatherDto(weathers);
    }

    @Transactional
    public List<WeatherDto> fetchWeathersByPlace(Long placeId) {
        List<Weather> weatherList = weatherRepository.fetchWeathersByPlace(placeId);
        return convertToListWeatherDto(weatherList);
    }

    private WeatherDto convertToDto(Weather weather) {
        WeatherDto weatherDto = modelMapper.map(weather, WeatherDto.class);
        return weatherDto;
    }

    private List<WeatherDto> convertToListWeatherDto(List<Weather> weathers) {
        if (weathers.isEmpty())
            return new ArrayList<>();
        return weathers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
