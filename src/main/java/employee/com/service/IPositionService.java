package employee.com.service;

import java.util.List;

import employee.com.DTO.RoleDTO;

public interface IPositionService {
	List<RoleDTO> findAll();

	 void savePosition(RoleDTO dto); 

	void deletePosition(Long id);

	void updatePosition(RoleDTO dto, Long id);

	void deleteListUser(Long[] ids);
}
