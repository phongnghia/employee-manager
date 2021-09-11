package employee.com.service;

import employee.com.DTO.UserDTO;

public interface ILoginService {
	UserDTO loginPage(String email, String password);

	UserDTO createPassword(String email, String password, String newPassword);

	UserDTO sendEmail(String email);

	void senderEmail(String from, String to, String subject, String content);
}
