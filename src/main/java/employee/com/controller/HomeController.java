package employee.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import employee.com.DTO.UserDTO;
import employee.com.service.ILoginService;

@Controller
public class HomeController {

	@Autowired
	private ILoginService loginService;

	@RequestMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("dashboard");
		return mav;
	}

	@RequestMapping(value = "/forgot")
	public String sendEmail(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model) {
		UserDTO userDTO = new UserDTO();
		userDTO = loginService.loginPage(email, password);
		if (userDTO.getMessage().equals("true")) {
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "sendemail";
		} else {
			return "login";
		}
	}
}