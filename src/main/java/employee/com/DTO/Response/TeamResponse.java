package employee.com.DTO.Response;

public class TeamResponse {

	private Long id;
	private String TeamName;
	private String nameManager;
	private Long[] countNumber;
	
	
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameManager() {
		return nameManager;
	}
	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}
	public Long[] getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(Long[] countNumber) {
		this.countNumber = countNumber;
	}
	
}
