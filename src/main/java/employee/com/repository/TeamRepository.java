package employee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.com.entity.TeamEntity;
import employee.com.repository.custom.TeamRepositoryCustom;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>,TeamRepositoryCustom{

}
