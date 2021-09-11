package employee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.com.entity.AdvantageEntity;

public interface AdvantageRepository extends JpaRepository<AdvantageEntity, Long> {

}
