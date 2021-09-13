package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> { }
