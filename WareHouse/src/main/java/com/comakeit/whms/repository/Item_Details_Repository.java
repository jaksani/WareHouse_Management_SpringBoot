package com.comakeit.whms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comakeit.whms.bean.Item_Details;

@Repository
public interface Item_Details_Repository extends JpaRepository<Item_Details, Integer>{

}
