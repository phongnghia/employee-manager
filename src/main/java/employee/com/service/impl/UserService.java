package employee.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import employee.com.DTO.UserDTO;
import employee.com.converter.UserConverter;
import employee.com.entity.RoleEntity;
import employee.com.entity.TeamEntity;
import employee.com.entity.UserEntity;
import employee.com.repository.RoleRepository;
import employee.com.repository.TeamRepository;
import employee.com.repository.UserRepository;
import employee.com.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private RoleRepository positionRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> result = new ArrayList<>();
		List<UserEntity> entity = userRepository.findAll();
		for (UserEntity userEntity : entity) {
			UserDTO dto = new UserDTO();
			if (userEntity.getPosition() == null) {
				dto = userConverter.toDto(userEntity);
				dto.setRoleName("Intern");
				result.add(dto);
			} else {
				dto = userConverter.toDto(userEntity);
				dto.setRoleName(userEntity.getPosition().getName());
				result.add(dto);
			}
		}
		return result;
	}

	@Override
	public List<UserDTO> findAllById(long id) {
		List<UserDTO> result = new ArrayList<UserDTO>();
		List<UserEntity> entity = userRepository.findAll();
		for (UserEntity userEntity : entity) {
			if (userEntity.getPosition().getId() == id) {
				UserDTO dto = new UserDTO();
				dto = userConverter.toDto(userEntity);
				dto.setRoleName(userEntity.getPosition().getName());
				result.add(dto);
			}
		}
		return result;
	}

	@Override
	public UserDTO findById(Long id) {
		UserEntity userEntity = userRepository.findOne(id);
		UserDTO dto = new UserDTO();
		dto = userConverter.toDto(userEntity);
		if (userEntity.getPosition() == null && userEntity.getTeam() == null) {
			dto = userConverter.toDto(userEntity);
		} else if (userEntity.getTeam() == null) {
			dto = userConverter.toDto(userEntity);
			dto.setIdRole(userEntity.getPosition().getId());
			dto.setRoleName(userEntity.getPosition().getName());
		} else if (userEntity.getPosition() == null) {
			dto = userConverter.toDto(userEntity);
			dto.setTeam(userEntity.getTeam().getName());
			dto.setIdTeam(userEntity.getTeam().getId());
		} else {
			dto = userConverter.toDto(userEntity);
			dto.setTeam(userEntity.getTeam().getName());
			dto.setIdTeam(userEntity.getTeam().getId());
			dto.setIdRole(userEntity.getPosition().getId());
			dto.setRoleName(userEntity.getPosition().getName());
		}
		dto.setImage(userEntity.getImage());
		return dto;
	}

	@Override
	public void saveUser(UserDTO dto) {
		UserEntity userEntity = new UserEntity();
		userEntity = userConverter.toEntity(dto);
		RoleEntity position = positionRepository.findOne(dto.getIdRole());
		userEntity.setPassword(getMD5(dto.getPassword()));
		userEntity.setPosition(position);
		userEntity.setImage("default.png");
		userRepository.save(userEntity);
	}

	@Override
	public void deleteUser(Long id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		userRepository.delete(id);

	}

	@Override
	public UserDTO updateUser(UserDTO dto, Long id) {
		UserEntity userEntity = new UserEntity();
		userEntity = userRepository.findOne(id);
		userEntity.setAge(dto.getAge());
		userEntity.setDayStart(dto.getDayStart());
		userEntity.setEmail(dto.getEmail());
		userEntity.setName(dto.getName());
		userEntity.setNickName(dto.getNickName());
		userEntity.setProfile(dto.getProfile());
		userEntity.setSex(dto.getSex());
		userEntity.setStatus(dto.getStatus());
		userEntity.setImage(dto.getImage());
		RoleEntity positionEntity = positionRepository.findOne(dto.getIdRole());
		userEntity.setPosition(positionEntity);
		TeamEntity teamEntity = teamRepository.findOne(dto.getIdTeam());
		userEntity.setTeam(teamEntity);
		UserDTO userDTO = new UserDTO();
		userRepository.save(userEntity);
		userDTO.setMessage("true");
		return userDTO;
	}

	@Override
	public void deleteListUser(Long[] ids) {
		for (Long id : ids) {
			userRepository.delete(id);
		}
	}

	@Override
	public List<UserDTO> findUserByTeamId(Long teamid) {
		List<UserDTO> result = new ArrayList<>();
		List<UserEntity> entity = userRepository.findUserByIdTeam(teamid);
		for (UserEntity userEntity : entity) {
			UserDTO dto = userConverter.toDto(userEntity);

			if (userEntity.getTeam() != null) {
				dto.setTeam(userEntity.getTeam().getName());
				if (userEntity.getTeam().getId() == teamid) {
					dto.setChecked("checked");
				}
			}

			if (userEntity.getPosition().equals("MANAGER")) {
				dto.setPosiTion(userEntity.getPosition().getName());
			}
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<UserDTO> findUserByPositionName(String name) {
		List<UserDTO> result = new ArrayList<>();
		List<UserEntity> entity = userRepository.findUserByPositionName(name);
		for (UserEntity userEntity : entity) {
			result.add(userConverter.toDto(userEntity));
		}
		return result;
	}

	@Override
	public UserDTO findUserByUserid(Long userid) {
		UserDTO userDTO = new UserDTO();
		UserEntity userEntity = userRepository.findOne(userid);
		userDTO = userConverter.toDto(userEntity);
		userDTO.setImage(userEntity.getImage());
		userDTO.setRoleName(userEntity.getPosition().getName());
		return userDTO;
	}

	// Check role user
	@Override
	public UserDTO checkRole(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		UserDTO userDTO = new UserDTO();
		if (userEntity != null) {
			userDTO.setRoleName(userEntity.getPosition().getName());
			userDTO.setMessage("true");
		} else {
			userDTO.setMessage("false");
		}
		return userDTO;
	}

	// MD5
	@Override
	public String getMD5(String password) {
		String hashPass = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
		return hashPass;
	}

	@Override
	public UserDTO findByIdUserTeam(Long id) {
		return userConverter.toDto(userRepository.findOne(id));
	}

}
