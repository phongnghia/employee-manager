package employee.com.service;

import java.util.List;

import employee.com.DTO.TechnicalDTO;

public interface ITechnicalService {
	
//	TechnicalDTO getTechnical(Long id);
	
	List<TechnicalDTO> findAll(Long id);

	void saveTechnical(Long id, TechnicalDTO technicaldto);
	
	void deleteTechnical(Long[] ids);
	
	void updateTechnical(TechnicalDTO technicaldto, Long id);
}
