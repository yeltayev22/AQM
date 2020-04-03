package kz.yeltayev.aqm.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private BigDecimal temperature;

    @Column(name = "humidity")
    private BigDecimal humidity;

    @Column(name = "pressure")
    private BigDecimal pressure;

    @Column(name = "date")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}
