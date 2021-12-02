package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
	@Query("select u from Bill u where u.userInfo.id = ?1 and status=true")
	public List<Bill> getBillByUser(int id);
}


