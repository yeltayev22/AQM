package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.exception.ResourceNotFoundException;
import kz.yeltayev.aqm.model.dto.TemperatureDto;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.model.entity.Temperature;
import kz.yeltayev.aqm.model.request.TemperatureRequest;
import kz.yeltayev.aqm.repository.PlaceRepository;
import kz.yeltayev.aqm.repository.TemperatureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    private TemperatureRepository temperatureRepository;
    private PlaceRepository placeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository, PlaceRepository placeRepository) {
        this.temperatureRepository = temperatureRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional
    public TemperatureDto addTemperature(TemperatureRequest temperatureRequest) throws ResourceNotFoundException {
        Temperature temperature = new Temperature();

        temperature.setDateTime(temperatureRequest.getDateTime());
        temperature.setHumidity(temperatureRequest.getHumidity());
        temperature.setTemperature(temperatureRequest.getTemperature());

        Place place = placeRepository.findById(temperatureRequest.getPlaceId()).orElseThrow(
                () -> new ResourceNotFoundException("Place not found for this id : " + temperatureRequest.getPlaceId()));

        temperature.setPlace(place);

        return convertToDto(temperatureRepository.save(temperature));
    }

    @Transactional
    public List<TemperatureDto> fetchTemperatures() {
        List<Temperature> temperatures = temperatureRepository.findAll();
        return convertToListTemperatureDto(temperatures);
    }

    @Transactional
    public List<TemperatureDto> fetchTemperaturesByPlace(Long placeId) {
        List<Temperature> temperatureList = temperatureRepository.fetchTemperaturesByPlace(placeId);
        return convertToListTemperatureDto(temperatureList);
    }

    private TemperatureDto convertToDto(Temperature temperature) {
        TemperatureDto temperatureDto = modelMapper.map(temperature, TemperatureDto.class);
        return temperatureDto;
    }

    private List<TemperatureDto> convertToListTemperatureDto(List<Temperature> temperatures) {
        if (temperatures.isEmpty())
            return new ArrayList<>();
        return temperatures.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
