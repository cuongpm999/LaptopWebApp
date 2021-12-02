package vn.ptit.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.LaptopStat;
import vn.ptit.entities.UserInfo;
import vn.ptit.entities.UserStat;

@Service
public class UserService {
	@PersistenceContext
	protected EntityManager entityManager;

	public UserInfo loadUserByUsername(String userName) {
		try {
			String jpql = "select p from UserInfo p where p.username='" + userName + "' and p.status=true";
			Query query = entityManager.createQuery(jpql, UserInfo.class);
			List<UserInfo> list = query.getResultList();
			if (list.isEmpty())
				return null;
			return (UserInfo) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<UserStat> stats() {
		String sql = "SELECT tbl_user.*, A.SoTienMua FROM tbl_user, (SELECT SUM (all_money) AS SoTienMua, user_id FROM tbl_bill,tbl_payment WHERE tbl_bill.id = tbl_payment.bill_id GROUP BY user_id) AS A WHERE tbl_user.id = A.user_id";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> records = query.getResultList();
		List<UserStat> userStats = new ArrayList<>();
		for (int i = 0; i < records.size(); i++) {
			UserStat userStat = new UserStat();
			userStat.setId(Integer.parseInt(records.get(i)[0].toString()));
			userStat.setFullname(records.get(i)[3].toString());
			if (records.get(i)[4] != null) {
				userStat.setPhonenumber(records.get(i)[4].toString());
			}
			userStat.setEmail(records.get(i)[5].toString());
			userStat.setAddress(records.get(i)[7].toString());
			BigDecimal bigDecimal = new BigDecimal(records.get(i)[8].toString());
			userStat.setTongTienMua(bigDecimal);
			userStats.add(userStat);
		}

		Collections.sort(userStats, new Comparator<UserStat>() {

			@Override
			public int compare(UserStat o1, UserStat o2) {

				return o2.getTongTienMua().compareTo(o1.getTongTienMua());
			}
		});

		return userStats;
	}
}
