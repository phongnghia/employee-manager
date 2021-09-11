package employee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.com.entity.UserEntity;
import employee.com.repository.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<UserEntity, Long>,UserRepositoryCustom{
	// Find email
	UserEntity findByEmail(String email);
	
	List<UserEntity> findByTeam_id(Long id);
}
