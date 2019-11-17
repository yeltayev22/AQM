package kz.yeltayev.aqm.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "place")
public class Place {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lon")
    private BigDecimal longitude;

    @Column(name = "lat")
    private BigDecimal latitude;

    @Column(name = "name")
    private String name;

    @Column(name = "temperature")
    private BigDecimal temperature;

    @OneToMany(mappedBy = "place")
    private List<Gas> gasList;
}
