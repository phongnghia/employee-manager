package employee.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.com.DTO.RoleDTO;
import employee.com.entity.RoleEntity;
import employee.com.entity.UserEntity;
import employee.com.repository.RoleRepository;
import employee.com.repository.UserRepository;
import employee.com.service.IPositionService;

@Service
public class RoleService implements IPositionService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<RoleDTO> findAll() {
		List<RoleDTO> result = new ArrayList<>();
		List<RoleEntity> entity = roleRepository.findAll();
		for (RoleEntity positionEntity : entity) {
			RoleDTO dto = new RoleDTO();
			dto.setId(positionEntity.getId());
			dto.setName(positionEntity.getName());
			result.add(dto);
		}
		return result;
	}

	@Override
	public void savePosition(RoleDTO dto) {

		RoleEntity entity = new RoleEntity();
		entity.setName(dto.getName());
		roleRepository.save(entity);

	}

	@Override
	public void deletePosition(Long id) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity = roleRepository.findOne(id);

		for (UserEntity userentity : roleEntity.getUsers()) {
			if (userentity.getPosition().getId() == id) {
				roleEntity.setId((long) 1);
				userentity.setPosition(roleEntity);
				userRepository.save(userentity);
			}
		}
		roleRepository.delete(id);
	}

	@Override
	public void updatePosition(RoleDTO dto, Long id) {
		RoleEntity entity = new RoleEntity();
		entity.setName(dto.getName());
		entity.setId(id);
		roleRepository.save(entity);
	}

	@Override
	public void deleteListUser(Long[] ids) {
		for (Long id : ids) {
			roleRepository.delete(id);
		}
	}

}
