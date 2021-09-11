package employee.com.DTO;

import java.util.ArrayList;
import java.util.List;

public class TeamDTO extends AbstactDTO<TeamDTO>{
	private String name;
	private String nameManager;
	private Integer number;
	private Long[] countNumber;
	private Long getidUsers ;
	private String exPe;
	private List<UserDTO> users = new ArrayList<>();
	


	
	public String getExPe() {
		return exPe;
	}
	public void setExPe(String exPe) {
		this.exPe = exPe;
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameManager() {
		return nameManager;
	}
	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Long[] getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(Long[] countNumber) {
		this.countNumber = countNumber;
	}
	public Long getGetidUsers() {
		return getidUsers;
	}
	public void setGetidUsers(Long getidUsers) {
		this.getidUsers = getidUsers;
	}
	
	
}
