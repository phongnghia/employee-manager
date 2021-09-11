package employee.com.DTO.Request;


import java.util.ArrayList;
import java.util.List;

import employee.com.DTO.UserDTO;

public class TeamRrequest {
	
	private String nameTeam;
	private List<UserDTO> listallUser = new ArrayList<>();
	private List<UserDTO> listmanager = new ArrayList<>();
	
	private String managername;
	private Long[] checkeds;
	private Long idTeam;
	
	
	public List<UserDTO> getListmanager() {
		return listmanager;
	}
	public void setListmanager(List<UserDTO> listmanager) {
		this.listmanager = listmanager;
	}
	public String getNameTeam() {
		return nameTeam;
	}
	public void setNameTeam(String nameTeam) {
		this.nameTeam = nameTeam;
	}
	public List<UserDTO> getListallUser() {
		return listallUser;
	}
	public void setListallUser(List<UserDTO> listallUser) {
		this.listallUser = listallUser;
	}
	
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	
	public Long[] getCheckeds() {
		return checkeds;
	}
	public void setCheckeds(Long[] checkeds) {
		this.checkeds = checkeds;
	}
	public Long getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}
	
	
	
	
}
