package employee.com.repository.custom;

import java.util.List;

import employee.com.entity.UserEntity;

public interface UserRepositoryCustom {

	List<UserEntity> findUserByIdTeam(Long idteam);

	List<UserEntity> findUserByPositionName(String name);

	List<UserEntity> updateIdteamUserByIdteam(Long id);
}
