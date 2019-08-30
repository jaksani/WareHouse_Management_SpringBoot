package com.WareHouseManagement.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WareHouseManagement.spring.Entities.Customer_Details;

@Repository
public interface Customer_Details_Interface extends JpaRepository<Customer_Details,Integer>{

}
