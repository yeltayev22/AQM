package kz.yeltayev.aqm.repository;

import kz.yeltayev.aqm.model.entity.Gas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasRepository extends JpaRepository<Gas, Long> {
}
