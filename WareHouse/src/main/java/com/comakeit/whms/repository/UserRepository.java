package com.comakeit.whms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comakeit.whms.bean.User;
@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
