package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.TotalVisit;

@Repository
public interface TotalVisitRepository extends JpaRepository<TotalVisit, Integer>{

}