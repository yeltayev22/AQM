package kz.yeltayev.aqm.repository;

import kz.yeltayev.aqm.model.entity.Place;
import kz.yeltayev.aqm.model.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "SELECT * FROM Place place WHERE place.access_code = ?1", nativeQuery = true)
    Place findByAccessCode(String accessCode);

}
