package kz.yeltayev.aqm.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(name = "index")
    private BigDecimal index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}
