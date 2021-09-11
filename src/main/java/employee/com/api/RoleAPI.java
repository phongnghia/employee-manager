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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee.com.DTO.RoleDTO;
import employee.com.service.IPositionService;

@RestController
@RequestMapping("/api/position")
public class RoleAPI {
	@Autowired
	private IPositionService iPositionService;

	@GetMapping
	public List<RoleDTO> getPosition() {
		return iPositionService.findAll();
	}

	@PostMapping
	public void insertPosition(@RequestBody RoleDTO positionDTO) {
		iPositionService.savePosition(positionDTO);
	}

	@PutMapping("/{id}")
	public void updatePosition(@RequestBody RoleDTO dto, @PathVariable Long id) {
		iPositionService.updatePosition(dto, id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deletePosition(@RequestParam Long id) {
		iPositionService.deletePosition(id);
	}

	@DeleteMapping("/{ids}")
	public void deleteListPosition(@PathVariable Long[] ids) {
		iPositionService.deleteListUser(ids);
	}

}
