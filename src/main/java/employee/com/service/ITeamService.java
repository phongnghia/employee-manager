package employee.com.service;

import employee.com.DTO.TeamDTO;
import employee.com.DTO.Request.TeamRrequest;

public interface ITeamService {

	TeamDTO findAll();

	TeamDTO InsertTeam(TeamDTO dto);

	TeamDTO UpdateTeam(TeamRrequest dto);

	void deleteTeam(Long[] ids);

	TeamDTO findOneByid(Long id);

//	List<TeamDTO> LoadTeambeforupdate(Long teamid);
//	TeamDTO updateTeam(TeamDTO dto); 
	int getTotalItem();

	TeamDTO Inforteam(Long TeamId);
	
}
