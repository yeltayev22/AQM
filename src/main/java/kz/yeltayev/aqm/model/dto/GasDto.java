package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GasDto {
    private Long id;
    private BigDecimal hydrogen;
    private BigDecimal carbonMonoxide;
    private BigDecimal ammonia;
    private BigDecimal h2s;
    private LocalDateTime dateTime;
}
