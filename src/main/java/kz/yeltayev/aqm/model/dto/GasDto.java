package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GasDto {
    private Long id;
    private BigDecimal tgs2600;
    private BigDecimal tgs2602;
    private LocalDateTime dateTime;
}
