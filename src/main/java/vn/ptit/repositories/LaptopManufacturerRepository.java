package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.LaptopManufacturer;

@Repository
public interface LaptopManufacturerRepository extends JpaRepository<LaptopManufacturer, Integer>{

}
