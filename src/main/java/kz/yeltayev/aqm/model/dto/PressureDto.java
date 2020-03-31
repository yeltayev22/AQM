package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PressureDto {
    private Long id;
    private BigDecimal pressure;
    private LocalDateTime dateTime;
}
