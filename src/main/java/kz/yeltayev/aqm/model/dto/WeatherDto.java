package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WeatherDto {
    private Long id;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal pressure;
    private LocalDateTime dateTime;
}
