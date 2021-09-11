package employee.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.com.DTO.UserDTO;
import employee.com.DTO.Request.TeamRrequest;
import employee.com.converter.UserConverter;
import employee.com.entity.TeamEntity;
import employee.com.entity.UserEntity;
import employee.com.repository.TeamRepository;
import employee.com.repository.UserRepository;
import employee.com.service.ITeamRequestService;


@Service
public class TeamRequestService implements ITeamRequestService{
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private UserConverter userconverter;
	
	@Autowired
	private TeamRepository teamrepository;

	@Override
	public TeamRrequest findAll(Long teamId) {
		TeamRrequest result = new TeamRrequest();
		// find all users
		List<UserEntity> userentity = userrepository.findAll();
		//find one team by teamId
		TeamEntity teamentity = teamrepository.findOne(teamId);
		
		List<UserDTO> usertdto = new ArrayList<>();
		List<UserDTO> namemanagers = new  ArrayList<>();
		
		// display user where role = MANAGER and teamId != null
		for (UserEntity userEntity : userentity) {
			UserDTO dto = userconverter.toDto(userEntity);
			//check teamId -> true
			if(userEntity.getTeam() != null) {
				if(userEntity.getTeam().getId() == teamId) {
					dto.setChecked("true");
				}	
			}
			//checked positionId = teamId
			if(userEntity.getPosition().getName().equals("MANAGER")) {
				namemanagers.add(dto);
			}
			if(!userEntity.getPosition().getName().equals("MANAGER")) {
				usertdto.add(dto);
			}
			
		}
		
		result.setListallUser(usertdto);
		result.setListmanager(namemanagers);
		result.setIdTeam(teamId);
		result.setManagername(teamentity.getNameManager());
		//get nameTeam
		result.setNameTeam(teamentity.getName());
		
		return result;
	}

}
