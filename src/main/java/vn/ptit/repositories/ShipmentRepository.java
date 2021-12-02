package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{
	List<Shipment> findByStatus(Boolean status);
}
