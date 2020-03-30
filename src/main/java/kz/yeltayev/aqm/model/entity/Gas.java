package kz.yeltayev.aqm.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "gas")
public class Gas {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pressure")
    private BigDecimal pressure;

    @Column(name = "temperature")
    private BigDecimal temperature;

    @Column(name = "air_quality_index")
    private BigDecimal airQualityIndex;

    @Column(name = "date")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}
