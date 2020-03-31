package kz.yeltayev.aqm.controller;

import kz.yeltayev.aqm.model.dto.GasDto;
import kz.yeltayev.aqm.model.entity.Gas;
import kz.yeltayev.aqm.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GasController {

    private GasService gasService;

    @Autowired
    public GasController(GasService gasService) {
        this.gasService = gasService;
    }

    @GetMapping("/gases")
    public List<GasDto> fetchGases() {
        return gasService.fetchGases();
    }

    @GetMapping("/gases/{placeId}")
    public List<GasDto> fetchGasesByPlace(@PathVariable(value = "placeId") Long placeId) {
        List<GasDto> gasDtoList = gasService.fetchGasesByPlace(placeId);
        return gasDtoList;
    }

    @PostMapping("/gases")
    public GasDto addGas(@Valid @RequestBody Gas gas) {
        return gasService.addGas(gas);
    }
}
