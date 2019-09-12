package com.comakeit.whms.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.comakeit.whms.bean.Order_Details;

@Repository
public interface Order_Details_Interface extends JpaRepository<Order_Details, Integer>{

	@Query("select order from Order_Details order where order.manufacturer_name = :username")
	ArrayList<Order_Details> getListofOrders(@PathVariable("username") String username);

	@Query("select order from Order_Details order where order.manager_name = :username")
	ArrayList<Order_Details> getListMyOrders(@PathVariable("username") String username);

}
