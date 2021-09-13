package vn.ptit.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ptit.entities.BannerAttachment;
import vn.ptit.beans.FilterMap;
import vn.ptit.entities.Banner;
import vn.ptit.entities.Laptop;
import vn.ptit.entities.LaptopManufacturer;
import vn.ptit.repositories.LaptopRepository;

@Service
public class LaptopService {
	@Autowired
	public LaptopRepository laptopRepository;
	@PersistenceContext
	EntityManager entityManager;

	private static int LIMIT = 36;

	@SuppressWarnings("unchecked")
	public List<Laptop> searchManufacturerItem(String laptopManufacturerSeo, Integer pageNumber, List<FilterMap> list) {

		String jpql = "select p from Laptop p where status=true";
		if (laptopManufacturerSeo != null) {
			jpql += " and p.laptopManufacturer.seo='" + laptopManufacturerSeo + "'";

			for (FilterMap filterMap : list) {
				if (filterMap.getKey().equalsIgnoreCase("sort")) {
					if (filterMap.getValue().equalsIgnoreCase("low-to-high")) {
						jpql += " order by p.price asc";
					} else
						jpql += " order by p.price desc";

				} else if (filterMap.getKey().equalsIgnoreCase("price")) {
					if (filterMap.getValue().compareToIgnoreCase("duoi10trieu") == 0) {
						jpql += " and p.price<10000000";

					} else if (filterMap.getValue().compareToIgnoreCase("10den20trieu") == 0) {
						jpql += " and p.price>=10000000 and p.price<20000000";

					} else if (filterMap.getValue().compareToIgnoreCase("20den30trieu") == 0) {
						jpql += " and p.price>=20000000 and p.price<30000000";

					} else if (filterMap.getValue().compareToIgnoreCase("30den40trieu") == 0) {
						jpql += " and p.price>=30000000 and p.price<40000000";

					} else if (filterMap.getValue().compareToIgnoreCase("40den50trieu") == 0) {
						jpql += " and p.price>=40000000 and p.price<50000000";

					} else if (filterMap.getValue().compareToIgnoreCase("tren50trieu") == 0) {
						jpql += " and p.price>=50000000";
					}
				} else if (filterMap.getKey().equalsIgnoreCase("status")) {
					jpql += " and p.soLuongNhap>0";
				} else
					jpql += " and lower(p." + filterMap.getKey() + ") like '%" + filterMap.getValue().toLowerCase()
							+ "%'";
			}
		}
		Query query = entityManager.createQuery(jpql, Laptop.class);

		query.setFirstResult((pageNumber - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Laptop> getLaptopBySeo(String seo) {

		String jpql = "select p from Laptop p where status=true"; 
		if (seo != null) {
			jpql += " and p.seo='" + seo + "'";
		}
		Query query = entityManager.createQuery(jpql, Laptop.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Laptop> getLaptopManufacturerBySeo(String seo) {

		String jpql = "select p from Laptop p where status=true"; 
		if (seo != null) {
			jpql += " and p.laptopManufacturer.seo='" + seo + "'";
		}
		Query query = entityManager.createQuery(jpql, Laptop.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Laptop> searchAllItem(Integer pageNumber, List<FilterMap> list) {

		String jpql = "select p from Laptop p where status=true";
		for (FilterMap filterMap : list) {
			if (filterMap.getKey().equalsIgnoreCase("sort")) {
				if (filterMap.getValue().equalsIgnoreCase("low-to-high")) {
					jpql += " order by p.price asc";
				} else
					jpql += " order by p.price desc";

			} else if (filterMap.getKey().equalsIgnoreCase("price")) {
				if (filterMap.getValue().compareToIgnoreCase("duoi10trieu") == 0) {
					jpql += " and p.price<10000000";

				} else if (filterMap.getValue().compareToIgnoreCase("10den20trieu") == 0) {
					jpql += " and p.price>=10000000 and p.price<20000000";

				} else if (filterMap.getValue().compareToIgnoreCase("20den30trieu") == 0) {
					jpql += " and p.price>=20000000 and p.price<30000000";

				} else if (filterMap.getValue().compareToIgnoreCase("30den40trieu") == 0) {
					jpql += " and p.price>=30000000 and p.price<40000000";

				} else if (filterMap.getValue().compareToIgnoreCase("40den50trieu") == 0) {
					jpql += " and p.price>=40000000 and p.price<50000000";

				} else if (filterMap.getValue().compareToIgnoreCase("tren50trieu") == 0) {
					jpql += " and p.price>=50000000";
				}
			} else if (filterMap.getKey().equalsIgnoreCase("status")) {
				jpql += " and p.soLuongNhap>0";
			} else
				jpql += " and lower(p." + filterMap.getKey() + ") like '%" + filterMap.getValue().toLowerCase() + "%'";
		}

		Query query = entityManager.createQuery(jpql, Laptop.class);

		query.setFirstResult((pageNumber - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<LaptopManufacturer> getAllLaptopManufacturer() {

		String jpql = "select p from LaptopManufacturer p where status=true"; 
		Query query = entityManager.createQuery(jpql, LaptopManufacturer.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Banner> getBanner() {

		String jpql = "select p from Banner p where status=true"; 
		Query query = entityManager.createQuery(jpql, Banner.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Laptop> searchName(String tenLaptop, Integer pageNumber, List<FilterMap> list) {

		if (!tenLaptop.isEmpty() && !tenLaptop.equalsIgnoreCase("'")) {
			String jpql = "select p from Laptop p where status=true and lower(p.name) like" + "'%"
					+ tenLaptop.toLowerCase() + "%'";
			for (FilterMap filterMap : list) {
				if (filterMap.getKey().equalsIgnoreCase("sort")) {
					if (filterMap.getValue().equalsIgnoreCase("low-to-high")) {
						jpql += " order by p.price asc";
					} else
						jpql += " order by p.price desc";

				} else if (filterMap.getKey().equalsIgnoreCase("price")) {
					if (filterMap.getValue().compareToIgnoreCase("duoi10trieu") == 0) {
						jpql += " and p.price<10000000";

					} else if (filterMap.getValue().compareToIgnoreCase("10den20trieu") == 0) {
						jpql += " and p.price>=10000000 and p.price<20000000";

					} else if (filterMap.getValue().compareToIgnoreCase("20den30trieu") == 0) {
						jpql += " and p.price>=20000000 and p.price<30000000";

					} else if (filterMap.getValue().compareToIgnoreCase("30den40trieu") == 0) {
						jpql += " and p.price>=30000000 and p.price<40000000";

					} else if (filterMap.getValue().compareToIgnoreCase("40den50trieu") == 0) {
						jpql += " and p.price>=40000000 and p.price<50000000";

					} else if (filterMap.getValue().compareToIgnoreCase("tren50trieu") == 0) {
						jpql += " and p.price>=50000000";
					}
				} else if (filterMap.getKey().equalsIgnoreCase("status")) {
					jpql += " and p.soLuongNhap>0";
				} else
					jpql += " and lower(p." + filterMap.getKey() + ") like '%" + filterMap.getValue().toLowerCase()
							+ "%'";
			}

			Query query = entityManager.createQuery(jpql, Laptop.class);
			query.setFirstResult((pageNumber - 1) * LIMIT);
			query.setMaxResults(LIMIT);

			return query.getResultList();
		}
		return null;
	}
	
	public List<Laptop> searchNameAuto(String tenLaptop) {

		if (!tenLaptop.isEmpty() && !tenLaptop.equalsIgnoreCase("'")) {
			String jpql = "select p from Laptop p where status=true and lower(p.name) like" + "'%" + tenLaptop.toLowerCase() + "%'";
			Query query = entityManager.createQuery(jpql, Laptop.class);
			return query.getResultList();
		}
		return null;	
	}

	@SuppressWarnings("unchecked")
	public List<Laptop> filterBuySale(Integer pageNumber, List<FilterMap> list) {

		String jpql = "select p from Laptop p where 1=1 and p.khuyenMai is not null";

		for (FilterMap filterMap : list) {
			if (filterMap.getKey().equalsIgnoreCase("sort")) {
				if (filterMap.getValue().equalsIgnoreCase("low-to-high")) {
					jpql += " order by p.price asc";
				} else
					jpql += " order by p.price desc";

			} else if (filterMap.getKey().equalsIgnoreCase("price")) {
				if (filterMap.getValue().compareToIgnoreCase("duoi10trieu") == 0) {
					jpql += " and p.price<10000000";

				} else if (filterMap.getValue().compareToIgnoreCase("10den20trieu") == 0) {
					jpql += " and p.price>=10000000 and p.price<20000000";

				} else if (filterMap.getValue().compareToIgnoreCase("20den30trieu") == 0) {
					jpql += " and p.price>=20000000 and p.price<30000000";

				} else if (filterMap.getValue().compareToIgnoreCase("30den40trieu") == 0) {
					jpql += " and p.price>=30000000 and p.price<40000000";

				} else if (filterMap.getValue().compareToIgnoreCase("40den50trieu") == 0) {
					jpql += " and p.price>=40000000 and p.price<50000000";

				} else if (filterMap.getValue().compareToIgnoreCase("tren50trieu") == 0) {
					jpql += " and p.price>=50000000";
				}
			} else if (filterMap.getKey().equalsIgnoreCase("status")) {
				jpql += " and p.soLuongNhap>0";
			} else
				jpql += " and lower(p." + filterMap.getKey() + ") like '%" + filterMap.getValue().toLowerCase() + "%'";
		}

		Query query = entityManager.createQuery(jpql, Laptop.class);

		query.setFirstResult((pageNumber - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		return query.getResultList();
	}

}
