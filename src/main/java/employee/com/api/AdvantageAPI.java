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

import employee.com.DTO.AdvantageDTO;
import employee.com.service.IAdvantageService;

@RestController
@RequestMapping(value = "/")
public class AdvantageAPI {

	@Autowired
	private IAdvantageService iadvantageservice;

	@GetMapping("{idU}/api/advantage")
	public List<AdvantageDTO> getListAdvantage(@PathVariable Long idU) {
		return iadvantageservice.findAll(idU);
	}

	@PostMapping("{idU}/api/advantage")
	public void insertAdvantage(@PathVariable Long idU, @RequestBody AdvantageDTO advantage) {
		iadvantageservice.saveAdvantage(idU, advantage);
	}

//	@DeleteMapping("/{id}")
//	public void deleteAdvantage(@PathVariable Long id) {
//		iadvantageservice.deleteAdvantage(id);
//	}
	
	@DeleteMapping("{idU}/api/advantage/{idAds}")
	public void deleteListAdvantage(@PathVariable("idAds") Long[] ids) {
		iadvantageservice.deleteListAdvantage(ids);
	}
	
	
	@PutMapping("api/advantage/{idAd}")
	public void updateAdvantage(@RequestBody AdvantageDTO advantage, @PathVariable("idAd") Long id) {
		iadvantageservice.updateAdvantage(advantage, id);
	}
}
