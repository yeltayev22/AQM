package kz.yeltayev.aqm.model.response;

import lombok.Data;

@Data
public class AirVisualData {
    private String city;
    private String state;
    private String country;
    private AirVisualCurrent current;
}
