package com.WareHouseManagement.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WareHouseManagement.spring.Entities.User;
@Repository
public interface UserEntity extends JpaRepository<User, String>{

}
