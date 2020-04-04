package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceDto {
    private Long id;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String country;
    private String city;
    private int aqi;
    private GasDto gas;
    private WeatherDto weather;
}
