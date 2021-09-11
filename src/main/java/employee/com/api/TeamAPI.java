package employee.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee.com.DTO.TeamDTO;
import employee.com.DTO.Request.TeamRrequest;
import employee.com.service.ITeamRequestService;
import employee.com.service.ITeamService;

@RestController
@RequestMapping(value = "/api/team")
public class TeamAPI {

	@Autowired
	private ITeamService iteamservice;

	@Autowired
	private ITeamRequestService itemrequest;

	@GetMapping
	public TeamDTO getlistteam() {
		return iteamservice.findAll();
	}

	@PostMapping
	public TeamDTO insertteam(@RequestBody TeamDTO dto) {
		return iteamservice.InsertTeam(dto);
	}

	@PutMapping
	public TeamDTO updateteam(@RequestBody TeamRrequest teamrequest) {
		return iteamservice.UpdateTeam(teamrequest);
	}

	@DeleteMapping("/{ids}")
	public void deleteteam(@PathVariable Long[] ids) {
		iteamservice.deleteTeam(ids);
	}

	@GetMapping("/{teamid}")
	public TeamDTO selectTeamByIteam(@PathVariable("teamid") Long teamid) {
		return iteamservice.findOneByid(teamid);
	}
	/* ===========loadUserAnd Insert or Update=========== */

	/* ===========/loadUserAnd Insert or Update=========== */

	@GetMapping("/loadteam/{teamid}")
	public TeamRrequest loadteambeforupdate(@PathVariable("teamid") Long teamid) {
		return itemrequest.findAll(teamid);
	}

	/* ===================inforteam============================== */
	@GetMapping("/inforteam/{teamId}")
	public TeamDTO inforteam(@PathVariable("teamId") Long teamId) {
		return iteamservice.Inforteam(teamId);
	}
	
	
}
