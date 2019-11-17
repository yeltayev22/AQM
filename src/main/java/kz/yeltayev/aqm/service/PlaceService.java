package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.model.dto.PlaceDto;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.repository.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Transactional
    public PlaceDto addPlace(Place place) {
        return convertToDto(placeRepository.save(place));
    }

    @Transactional
    public List<PlaceDto> fetchPlaces() {
        List<Place> places = placeRepository.findAll();
        return convertToListPlaceDto(places);
    }

    @Transactional
    public PlaceDto

    private PlaceDto convertToDto(Place place) {
        PlaceDto placeDto = modelMapper.map(place, PlaceDto.class);
        return placeDto;
    }

    private List<PlaceDto> convertToListPlaceDto(List<Place> places) {
        if (places.isEmpty())
            return new ArrayList<>();
        return places.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
