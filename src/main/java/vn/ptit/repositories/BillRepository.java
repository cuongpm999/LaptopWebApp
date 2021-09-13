package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> { }


