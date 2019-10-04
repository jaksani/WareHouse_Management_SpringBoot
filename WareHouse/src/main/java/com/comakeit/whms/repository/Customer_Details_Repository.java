package com.comakeit.whms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comakeit.whms.bean.Customer_Details;

@Repository
public interface Customer_Details_Repository extends JpaRepository<Customer_Details,Integer>{

}
