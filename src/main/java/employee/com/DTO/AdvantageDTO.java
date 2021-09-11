package employee.com.DTO;

public class AdvantageDTO extends AbstactDTO<AdvantageDTO> {

	private String name;

	private Long userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
