package vn.ptit.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Shipment;
import vn.ptit.entities.ShipmentStat;
import vn.ptit.entities.UserStat;

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
	
	public List<ShipmentStat> stats() {
		String sql = "SELECT tbl_shipment.*, A.SoLuong FROM tbl_shipment, (SELECT COUNT (id) AS SoLuong, shipment_id FROM tbl_bill GROUP BY shipment_id ORDER BY SoLuong DESC) AS A WHERE tbl_shipment.id = A.shipment_id";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> records = query.getResultList();
		List<ShipmentStat> shipmentStats = new ArrayList<>();
		for (int i = 0; i < records.size(); i++) {
			ShipmentStat shipmentStat = new ShipmentStat();
			shipmentStat.setId(Integer.parseInt(records.get(i)[0].toString()));
			shipmentStat.setName(records.get(i)[1].toString());
			BigDecimal bigDecimal = new BigDecimal(records.get(i)[2].toString());
			shipmentStat.setPrice(bigDecimal);
			shipmentStat.setMobile(records.get(i)[3].toString());
			shipmentStat.setQuantity(Integer.parseInt(records.get(i)[5].toString()));
			shipmentStats.add(shipmentStat);
		}

		return shipmentStats;
	}

}
