package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> { }


