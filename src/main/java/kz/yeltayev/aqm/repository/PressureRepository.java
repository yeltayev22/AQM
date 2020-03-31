package kz.yeltayev.aqm.repository;

import kz.yeltayev.aqm.model.entity.Pressure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PressureRepository extends JpaRepository<Pressure, Long> {

    @Query(value = "SELECT * FROM Pressure pressure WHERE pressure.place_id = ?1", nativeQuery = true)
    List<Pressure> fetchPressuresByPlace(Long placeId);
}
