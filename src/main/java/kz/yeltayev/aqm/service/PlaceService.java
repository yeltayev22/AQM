package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.model.dto.GasDto;
import kz.yeltayev.aqm.model.dto.PlaceDto;
import kz.yeltayev.aqm.model.dto.WeatherDto;
import kz.yeltayev.aqm.model.entity.Gas;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.model.entity.Weather;
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
        List<Place> places = placeRepository.fetchPublicPlaces();
        return convertToListPlaceDto(places);
    }

    @Transactional
    public PlaceDto fetchPlaceByAccessCode(String accessCode) {
        Place place = placeRepository.findByAccessCode(accessCode);
        return convertToDto(place);
    }

    private PlaceDto convertToDto(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setId(place.getId());
        placeDto.setLatitude(place.getLatitude());
        placeDto.setLongitude(place.getLongitude());
        placeDto.setAqi(place.getAqi());
        placeDto.setCountry(place.getCountry());
        placeDto.setCity(place.getCity());

        List<Gas> gasList = place.getGasList();
        if (!gasList.isEmpty()) {
            gasList.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));
            placeDto.setGas(modelMapper.map(gasList.get(0), GasDto.class));
        }

        List<Weather> weatherList = place.getWeatherList();
        if (!weatherList.isEmpty()) {
            weatherList.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));
            placeDto.setWeather(modelMapper.map(weatherList.get(0), WeatherDto.class));
        }

        return placeDto;
    }

    private List<PlaceDto> convertToListPlaceDto(List<Place> places) {
        if (places.isEmpty())
            return new ArrayList<>();
        return places.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
