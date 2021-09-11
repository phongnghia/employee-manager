package employee.com.repository.custom;

import java.util.List;

import employee.com.entity.TeamEntity;

public interface TeamRepositoryCustom {

	List<TeamEntity> findAll1();

	List<TeamEntity> LoadTeambeforupdate();
}
