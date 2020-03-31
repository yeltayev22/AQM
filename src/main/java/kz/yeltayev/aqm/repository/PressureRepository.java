package kz.yeltayev.aqm.repository;

import kz.yeltayev.aqm.model.entity.Pressure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PressureRepository extends JpaRepository<Pressure, Long> {
}
