package kz.yeltayev.aqm.repository;

import kz.yeltayev.aqm.model.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}
