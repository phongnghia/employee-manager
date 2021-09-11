package employee.com.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import employee.com.entity.UserEntity;
import employee.com.repository.custom.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserEntity> findUserByIdTeam(Long idteam) {
		try {
			StringBuilder sql = new StringBuilder("select u.*,p.name from user u");
			sql.append(" left join position p on u.positionid = p.id");
			sql.append(" inner join team t on t.id = u.teamid ");
			Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<UserEntity> findUserByPositionName(String name) {
		try {
			StringBuilder sql = new StringBuilder("select u.* from user u");
			sql.append(" left join position p on u.positionid = p.id where p.name = '" + name + "'");
			Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<UserEntity> updateIdteamUserByIdteam(Long id) {
		try {
			String sql = "UPDATE user SET teamid = null WHERE teamid = '" + id + "'";
			Query query = entityManager.createNativeQuery(sql, UserEntity.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();

		}
	}
}
