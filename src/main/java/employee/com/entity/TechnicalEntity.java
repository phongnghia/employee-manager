package employee.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "technical")
@Entity
public class TechnicalEntity extends BaseEntity{

	@Column
	private String skill;
	
	@Column
	private String level;
	
	
	//with UserEntity
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "userid")
	    private UserEntity user;

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
	
}
