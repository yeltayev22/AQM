package kz.yeltayev.aqm.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GasDto {
    private Long id;
    private BigDecimal pressure;
    private BigDecimal temperature;
    private BigDecimal index;
}
