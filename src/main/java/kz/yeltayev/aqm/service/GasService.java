package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.exception.ResourceNotFoundException;
import kz.yeltayev.aqm.model.dto.GasDto;
import kz.yeltayev.aqm.model.entity.Gas;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.model.request.GasRequest;
import kz.yeltayev.aqm.repository.GasRepository;
import kz.yeltayev.aqm.repository.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GasService {

    private GasRepository gasRepository;
    private PlaceRepository placeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public GasService(GasRepository gasRepository, PlaceRepository placeRepository) {
        this.gasRepository = gasRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional
    public GasDto addGas(GasRequest gasRequest) throws ResourceNotFoundException {
        Gas gas = new Gas();

        gas.setDateTime(gasRequest.getDateTime());
        gas.setTgs2600(gasRequest.getTgs2600());
        gas.setTgs2602(gasRequest.getTgs2602());

        Place place = placeRepository.findById(gasRequest.getPlaceId()).orElseThrow(
                () -> new ResourceNotFoundException("Place not found for this id : " + gasRequest.getPlaceId()));

        gas.setPlace(place);

        return convertToDto(gasRepository.save(gas));
    }

    @Transactional
    public List<GasDto> fetchGases() {
        List<Gas> gases = gasRepository.findAll();
        return convertToListGasDto(gases);
    }

    @Transactional
    public List<GasDto> fetchGasesByPlace(Long placeId) {
        List<Gas> gasList = gasRepository.fetchGasesByPlace(placeId);
        return convertToListGasDto(gasList);
    }

    private GasDto convertToDto(Gas gas) {
        GasDto gasDto = modelMapper.map(gas, GasDto.class);
        return gasDto;
    }

    private List<GasDto> convertToListGasDto(List<Gas> gases) {
        if (gases.isEmpty())
            return new ArrayList<>();
        return gases.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
