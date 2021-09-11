package employee.com.converter;

import org.springframework.stereotype.Component;

import employee.com.DTO.TeamDTO;
import employee.com.entity.TeamEntity;

@Component
public class TeamConverter {

	public TeamDTO todto(TeamEntity entity) {
		TeamDTO result = new TeamDTO();
		if(entity.getId() != null) {
			result.setId(entity.getId());
		}
		result.setName(entity.getName());
		
		if(entity.getNameManager() != null &&!entity.getNameManager().equals("---chon---")) {
			result.setNameManager(entity.getNameManager());
		}
		result.setNumber(entity.getNumber());
		return result;
	}

	public TeamEntity toentity(TeamDTO dto) {
		TeamEntity result = new TeamEntity();
		if (dto.getId() != null) {
			result.setId(dto.getId());
		}
		result.setName(dto.getName());
		return result;
	}

	public TeamEntity toentity(TeamDTO dto, TeamEntity entity) {
		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}
		entity.setName(dto.getName());
		if (dto.getCountNumber() != null) {
			entity.setNumber(dto.getCountNumber().length);
		}
		return entity;
	}
}
