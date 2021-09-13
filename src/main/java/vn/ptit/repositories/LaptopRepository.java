package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> { }
