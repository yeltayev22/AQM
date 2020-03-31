package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.model.dto.TemperatureDto;
import kz.yeltayev.aqm.model.entity.Temperature;
import kz.yeltayev.aqm.repository.TemperatureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    private TemperatureRepository temperatureRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Transactional
    public TemperatureDto addTemperature(Temperature temperature) {
        return convertToDto(temperatureRepository.save(temperature));
    }

    @Transactional
    public List<TemperatureDto> fetchTemperatures() {
        List<Temperature> temperatures = temperatureRepository.findAll();
        return convertToListTemperatureDto(temperatures);
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
