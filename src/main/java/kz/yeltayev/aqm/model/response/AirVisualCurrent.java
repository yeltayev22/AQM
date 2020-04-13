package kz.yeltayev.aqm.model.response;

import lombok.Data;

@Data
public class AirVisualCurrent {
    private AirVisualWeather weather;
    private AirVisualPollution pollution;
}
