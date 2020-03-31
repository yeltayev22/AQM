package kz.yeltayev.aqm.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TemperatureRequest {
    private BigDecimal temperature;
    private BigDecimal humidity;
    private LocalDateTime dateTime;
    private Long placeId;
}
