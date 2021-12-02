package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
	List<Laptop> findByStatus(Boolean status);
}
