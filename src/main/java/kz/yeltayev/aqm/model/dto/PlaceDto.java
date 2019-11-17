package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PlaceDto {
    private Long id;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String name;
    private BigDecimal temperature;
    private List<GasDto> gasList;
}
