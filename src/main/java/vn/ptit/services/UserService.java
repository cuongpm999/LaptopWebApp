package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.UserInfo;

@Service
public class UserService {
	@PersistenceContext
	protected EntityManager entityManager;

	public UserInfo loadUserByUsername(String userName) {
		try {
			String jpql = "select p from UserInfo p where p.username='" + userName +"' and p.status=true";
			Query query = entityManager.createQuery(jpql, UserInfo.class);
			List<UserInfo> list= query.getResultList();
			if(list.isEmpty()) return null;
			return (UserInfo) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
