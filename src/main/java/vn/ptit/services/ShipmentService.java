package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Shipment;

@Service
public class ShipmentService {
	@PersistenceContext
	EntityManager entityManager;

	public List<Shipment> findAllShipment() {
		String jpql = "select p from Shipment p where status=true";
		Query query = entityManager.createQuery(jpql, Shipment.class);
		return query.getResultList();
	}

	public Shipment findShipmentById(int id) {
		String jpql = "select p from Shipment p where status=true and p.id=" + id;
		Query query = entityManager.createQuery(jpql, Shipment.class);
		return (Shipment) query.getResultList().get(0);
	}

}
