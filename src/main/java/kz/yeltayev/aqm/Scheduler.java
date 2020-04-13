package kz.yeltayev.aqm;

import kz.yeltayev.aqm.exception.ErrorHandler;
import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.model.response.AirVisualResponse;
import kz.yeltayev.aqm.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private PlaceRepository placeRepository;

    public static final String AIR_VISUAL_BASE_URL = "https://api.airvisual.com/v2/nearest_city?";
    public static final String AIR_VISUAL_API_KEY = "dbf1ea95-9710-46e8-a593-36d3eec2b68d";

    /**
     * how it works: https://stackoverflow.com/questions/26147044/spring-cron-expression-for-every-day-101am
     */
    @Scheduled(cron = "0 15 1 * * *")
    public void dailyAirVisualScheduler() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("yeltayev22" + " Scheduler run " + strDate);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ErrorHandler());

        List<Place> places = placeRepository.findAll();
        for (Place place : places) {

            String uri = AIR_VISUAL_BASE_URL +
                    "lat=" + place.getLatitude() +
                    "&lon=" + place.getLongitude() +
                    "&key=" + AIR_VISUAL_API_KEY;

            System.out.println("yeltayev22 URL" + uri);
            AirVisualResponse response = restTemplate.getForObject(uri, AirVisualResponse.class);
            if (response != null) {
                if (!response.getStatus().equals("success")) {
                    System.out.println("yeltayev22 !!!!!!! Error from AirVisual api !!!!!!! ");
                } else {
                    place.setAqi(response.getData().getCurrent().getPollution().getAqius());
                    System.out.println("yeltayev22 Updated aqi for city " + place.getCity() + " to " + place.getAqi());
                    placeRepository.save(place);
                }
            } else {
                System.out.println("yeltayev22 !!!!!!! Response was null !!!!!!!");
            }
        }
    }
}