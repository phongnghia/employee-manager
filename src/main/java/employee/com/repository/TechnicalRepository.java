package employee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.com.entity.TechnicalEntity;

public interface TechnicalRepository extends JpaRepository<TechnicalEntity, Long> {

}
