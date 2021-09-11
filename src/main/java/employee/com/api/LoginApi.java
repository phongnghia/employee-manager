package employee.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee.com.DTO.UserDTO;
import employee.com.service.ILoginService;
import employee.com.service.IUserService;

@RestController
@RequestMapping(value = "/api")
public class LoginApi {

	@Autowired
	private ILoginService loginService;

	@Autowired
	private IUserService iuserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDTO login(@RequestBody UserDTO userDTO) {
		userDTO = loginService.loginPage(userDTO.getEmail(), userDTO.getPassword());
		return userDTO;
	}

	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public UserDTO createPassword(@RequestBody UserDTO userDTO) {
		userDTO = loginService.createPassword(userDTO.getEmail(), userDTO.getPassword(), userDTO.getNewPassword());
		return userDTO;
	}

	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public UserDTO sendEmail(@RequestParam String email) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		userDTO = loginService.sendEmail(userDTO.getEmail());
		return userDTO;
	}

	@RequestMapping(value = "/checkrole", method = RequestMethod.GET)
	public UserDTO checkRoleUser(@RequestParam String email) {
		UserDTO userDTO = new UserDTO();
		userDTO = iuserService.checkRole(email);
		return userDTO;
	}
}