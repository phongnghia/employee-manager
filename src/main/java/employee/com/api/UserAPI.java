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

import employee.com.DTO.UserDTO;
import employee.com.service.IUserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserAPI {

	@Autowired
	private IUserService iuserService;

	// Find employee
	@GetMapping("/{id}")
	public List<UserDTO> getEmployees(@PathVariable long id) {
		if (id == 0) {
			return iuserService.findAll();
		} else {
			return iuserService.findAllById(id);
		}
	}

	@GetMapping("/single/{id}")
	public UserDTO getEmployeesById(@PathVariable Long id) {
		return iuserService.findById(id);
	}

	@PostMapping
	public void insertUser(@RequestBody UserDTO dto) {
		iuserService.saveUser(dto);
	}

	@PutMapping("/{id}")
	public UserDTO updateUser(@RequestBody UserDTO dto, @PathVariable Long id) {
		UserDTO userDTO = new UserDTO();
		userDTO = iuserService.updateUser(dto, id);
		return userDTO;
	}

	@DeleteMapping("/{ids}")
	public void deleteListUser(@PathVariable Long[] ids) {
		iuserService.deleteListUser(ids);
	}

	@DeleteMapping
	public void deleteUser(@PathVariable Long id) {
		iuserService.deleteUser(id);
	}

	// Find team

	@GetMapping("/{teamid}/team")
	public List<UserDTO> getUserByteamid(@PathVariable("teamid") Long teamid) {
		return iuserService.findUserByTeamId(teamid);
	}

	@GetMapping("/{teamid}/position")
	public List<UserDTO> getUserByPositionName(@PathVariable("teamid") String name) {
		return iuserService.findUserByPositionName(name);
	}

	@GetMapping("/loaduser/{iduser}")
	public UserDTO getUserByidUser(@PathVariable("iduser") Long iduser) {
		return iuserService.findByIdUserTeam(iduser);
	}

	@GetMapping("/{userid}/inforteamitem")
	public UserDTO getUserByuserid(@PathVariable("userid") Long userid) {
		return iuserService.findUserByUserid(userid);
	}

}
