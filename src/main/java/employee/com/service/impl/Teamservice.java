package employee.com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.com.DTO.TeamDTO;
import employee.com.DTO.UserDTO;
import employee.com.DTO.Request.TeamRrequest;
import employee.com.converter.TeamConverter;
import employee.com.converter.UserConverter;
import employee.com.entity.TeamEntity;
import employee.com.entity.UserEntity;
import employee.com.repository.TeamRepository;
import employee.com.repository.UserRepository;
import employee.com.service.ITeamService;
import employee.com.utils.TeamUtils;

@Service
public class Teamservice implements ITeamService {

	@Autowired
	private TeamRepository teamrepository;

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private UserConverter userconverter;

	@Autowired
	private TeamConverter teamconverter;

	@Override
	public TeamDTO findAll() {
		List<TeamEntity> entitys = teamrepository.findAll();

		TeamDTO result = new TeamDTO();
		List<TeamDTO> listteam = new ArrayList<>();
		// java8
		entitys.forEach(teamv1 -> {
			TeamDTO teamDTO = teamconverter.todto(teamv1);
			teamv1.setNumber(TeamUtils.CountNumberTeam(teamv1.getNameManager(), teamv1.getUsers()));
			teamrepository.save(teamv1);
			listteam.add(teamDTO);

		});
		result.setListresult(listteam);
		return result;
	}

	@Override
	public TeamDTO InsertTeam(TeamDTO dto) {
		return teamconverter.todto(teamrepository.save(teamconverter.toentity(dto)));

	}

	@Override
	@Transactional
	public void deleteTeam(Long[] ids) {
		for (Long id : ids) {
			// update by teamid ve null
			List<UserEntity> users = userrepository.findByTeam_id(id);
			for (UserEntity user : users) {
				user.setTeam(null);
				userrepository.save(user);
			}
			teamrepository.delete(id);
		}
	}

	@Override
	public TeamDTO findOneByid(Long id) {
		TeamEntity entity = teamrepository.findOne(id);
		TeamDTO dto = teamconverter.todto(entity);
		/*
		 * List<UserDTO> userdto = new ArrayList<UserDTO>();
		 * 
		 * for (UserEntity userDTO2 : entity.getUsers()) {
		 * userdto.add(userconverter.toDto(userDTO2)); }
		 */
		List<UserDTO> userdto = entity.getUsers().stream().map(item -> userconverter.toDto(item))
				.collect(Collectors.toList());

		dto.setUsers(userdto);
		return dto;
	}

	@Override
	public TeamDTO Inforteam(Long teamId) {
		// find team by teamid
		TeamEntity result = teamrepository.findOne(teamId);
		TeamDTO dto = new TeamDTO();
		dto.setName(result.getName());
		dto.setId(result.getId());
		dto.setNameManager(result.getNameManager());
		List<UserDTO> listteam = result.getUsers().stream().map(item -> userconverter.toDto(item))
				.collect(Collectors.toList());
		dto.setUsers(listteam);
		return dto;
	}

	@Override
	@Transactional
	public TeamDTO UpdateTeam(TeamRrequest dto) {
		TeamEntity result = new TeamEntity();
		result.setId(dto.getIdTeam());
		result.setName(dto.getNameTeam());
		result.setNameManager(dto.getManagername());
		// find one user -> update user by team;
		for (Long iduser : dto.getCheckeds()) {
			UserEntity userEntity = userrepository.findOne(iduser);
			userEntity.setTeam(result);
			userrepository.save(userEntity);
		}

		result.setNumber(TeamUtils.CountNumberTeam(dto.getManagername(), dto.getCheckeds()));
		return teamconverter.todto(teamrepository.save(result));
	}

	@Override
	public int getTotalItem() {
		return (int) userrepository.count();
	}

}
