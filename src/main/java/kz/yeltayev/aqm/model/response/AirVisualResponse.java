package kz.yeltayev.aqm.model.response;

import lombok.Data;

@Data
public class AirVisualResponse {
    private String status;
    private AirVisualData data;
}
