package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.exception.ResourceNotFoundException;
import kz.yeltayev.aqm.model.dto.PressureDto;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.model.entity.Pressure;
import kz.yeltayev.aqm.model.entity.Temperature;
import kz.yeltayev.aqm.model.request.PressureRequest;
import kz.yeltayev.aqm.repository.PlaceRepository;
import kz.yeltayev.aqm.repository.PressureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PressureService {

    private PressureRepository pressureRepository;
    private PlaceRepository placeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PressureService(PressureRepository pressureRepository, PlaceRepository placeRepository) {
        this.pressureRepository = pressureRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional
    public PressureDto addPressure(PressureRequest pressureRequest) throws ResourceNotFoundException {

        Pressure pressure = new Pressure();

        pressure.setDateTime(pressureRequest.getDateTime());
        pressure.setPressure(pressureRequest.getPressure());

        Place place = placeRepository.findById(pressureRequest.getPlaceId()).orElseThrow(
                () -> new ResourceNotFoundException("Place not found for this id : " + pressureRequest.getPlaceId()));

        pressure.setPlace(place);

        return convertToDto(pressureRepository.save(pressure));
    }

    @Transactional
    public List<PressureDto> fetchPressures() {
        List<Pressure> pressures = pressureRepository.findAll();
        return convertToListPressureDto(pressures);
    }

    @Transactional
    public List<PressureDto> fetchPressuresByPlace(Long placeId) {
        List<Pressure> pressureList = pressureRepository.fetchPressuresByPlace(placeId);
        return convertToListPressureDto(pressureList);
    }

    private PressureDto convertToDto(Pressure pressure) {
        PressureDto pressureDto = modelMapper.map(pressure, PressureDto.class);
        return pressureDto;
    }

    private List<PressureDto> convertToListPressureDto(List<Pressure> pressures) {
        if (pressures.isEmpty())
            return new ArrayList<>();
        return pressures.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
