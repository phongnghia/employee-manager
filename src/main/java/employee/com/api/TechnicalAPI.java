package employee.com.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee.com.DTO.TechnicalDTO;
import employee.com.service.ITechnicalService;

@RestController
@RequestMapping(value = "/")
public class TechnicalAPI {

	@Autowired
	private ITechnicalService itechnicalservice;

	@GetMapping("{idU}/api/technical")
	public List<TechnicalDTO> getListTechnical(@PathVariable Long idU) {
		return itechnicalservice.findAll(idU);
	}

	@PostMapping("{idU}/api/technical")
	public void insertTechnical(@PathVariable Long idU, @RequestBody TechnicalDTO technical) {
		itechnicalservice.saveTechnical(idU, technical);
	}

	@DeleteMapping("{idU}/api/technical/{idTes}")
	public void deleteTechnical(@PathVariable("idTes") Long[] ids) {
		itechnicalservice.deleteTechnical(ids);
	}
	
	@PutMapping("/api/technical/{idTe}")
	public void updateTechnical(@RequestBody TechnicalDTO technical,@PathVariable("idTe") Long id) {
		itechnicalservice.updateTechnical(technical, id);
	}
}
