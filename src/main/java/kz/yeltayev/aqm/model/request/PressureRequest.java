package kz.yeltayev.aqm.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PressureRequest {
    private BigDecimal pressure;
    private LocalDateTime dateTime;
    private Long placeId;
}
