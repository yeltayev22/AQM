package kz.yeltayev.aqm.controller;

import kz.yeltayev.aqm.model.dto.PlaceDto;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places")
    public List<PlaceDto> fetchPlaces() {
        return placeService.fetchPlaces();
    }

    @PostMapping("/places")
    public PlaceDto addPlace(@Valid @RequestBody Place place) {
        return placeService.addPlace(place);
    }
}
