package kz.yeltayev.aqm.repository;

import kz.yeltayev.aqm.model.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {

    @Query(value = "SELECT * FROM Temperature temperature WHERE temperature.place_id = ?1", nativeQuery = true)
    List<Temperature> fetchTemperaturesByPlace(Long placeId);
}
