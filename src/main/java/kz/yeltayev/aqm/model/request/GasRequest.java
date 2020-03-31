package kz.yeltayev.aqm.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GasRequest {
    private BigDecimal hydrogen;
    private BigDecimal carbonMonoxide;
    private BigDecimal ammonia;
    private BigDecimal h2s;
    private LocalDateTime dateTime;
    private Long placeId;
}
