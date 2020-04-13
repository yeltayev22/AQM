package kz.yeltayev.aqm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AqmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AqmApplication.class, args);
    }
}
