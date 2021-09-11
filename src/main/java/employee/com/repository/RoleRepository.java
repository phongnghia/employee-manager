package employee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.com.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByName(String name);

}
