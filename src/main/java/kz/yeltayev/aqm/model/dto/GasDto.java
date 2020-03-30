package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GasDto {
    private Long id;
    private BigDecimal pressure;
    private BigDecimal temperature;
    private BigDecimal qualityIndex;
    private LocalDateTime dateTime;
}
