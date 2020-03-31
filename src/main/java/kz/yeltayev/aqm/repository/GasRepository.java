package kz.yeltayev.aqm.repository;

import kz.yeltayev.aqm.model.entity.Gas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GasRepository extends JpaRepository<Gas, Long> {

    @Query(value = "SELECT * FROM Gas gas WHERE gas.place_id = ?1", nativeQuery = true)
    List<Gas> fetchGasesByPlace(Long placeId);
}
