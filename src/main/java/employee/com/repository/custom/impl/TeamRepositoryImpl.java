package employee.com.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import employee.com.entity.TeamEntity;
import employee.com.repository.custom.TeamRepositoryCustom;

@Repository
public class TeamRepositoryImpl implements TeamRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TeamEntity> findAll1() {
		try {
			StringBuilder sql = new StringBuilder("select t.*,count(u.teamid) number from team t");
			sql.append(" left join user u on t.id = u.teamid group by u.teamid");
			Query query = entityManager.createNativeQuery(sql.toString(), TeamEntity.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<TeamEntity> LoadTeambeforupdate() {
		try {
			StringBuilder sql = new StringBuilder("select t.* from team t");
			sql.append(" inner join user u on t.id = u.teamid group by u.teamid");
			Query query = entityManager.createNativeQuery(sql.toString(), TeamEntity.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
