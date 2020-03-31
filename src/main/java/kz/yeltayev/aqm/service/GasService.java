package kz.yeltayev.aqm.service;

import kz.yeltayev.aqm.model.dto.GasDto;
import kz.yeltayev.aqm.model.entity.Gas;
import kz.yeltayev.aqm.repository.GasRepository;
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public GasService(GasRepository gasRepository) {
        this.gasRepository = gasRepository;
    }

    @Transactional
    public GasDto addGas(Gas gas) {
        return convertToDto(gasRepository.save(gas));
    }

    @Transactional
    public List<GasDto> fetchGases() {
        List<Gas> gases = gasRepository.findAll();
        return convertToListGasDto(gases);
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
