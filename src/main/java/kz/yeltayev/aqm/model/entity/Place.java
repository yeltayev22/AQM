package kz.yeltayev.aqm.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "place")
@Data
public class Place {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lon")
    private BigDecimal longitude;

    @Column(name = "lat")
    private BigDecimal latitude;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "place")
    private List<Gas> gasList;
}
