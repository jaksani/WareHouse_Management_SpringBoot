package com.comakeit.whms.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.comakeit.whms.bean.Purchase_Details;

@Repository
public interface Purchase_Details_Repository extends JpaRepository<Purchase_Details, Integer>{

	@Query("select purchase from Purchase_Details purchase where purchase.date_of_purchase = :date")
	ArrayList<Purchase_Details> getPurchaseList(@PathVariable("date") LocalDate date);

}
