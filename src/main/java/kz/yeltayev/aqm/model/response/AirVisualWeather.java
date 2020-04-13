package kz.yeltayev.aqm.model.response;

import lombok.Data;

@Data
public class AirVisualWeather {
    private String ts;
    private int tp;
    private int pr;
    private int hu;
    private int ws;
    private int wd;
}
