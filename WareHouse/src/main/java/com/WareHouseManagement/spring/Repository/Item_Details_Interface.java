package com.WareHouseManagement.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WareHouseManagement.spring.Entities.Item_Details;

@Repository
public interface Item_Details_Interface extends JpaRepository<Item_Details, Integer>{

}
