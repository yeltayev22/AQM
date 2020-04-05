package kz.yeltayev.aqm.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GasRequest {
    private BigDecimal tgs2600;
    private BigDecimal tgs2602;
    private LocalDateTime dateTime;
    private Long placeId;
}
