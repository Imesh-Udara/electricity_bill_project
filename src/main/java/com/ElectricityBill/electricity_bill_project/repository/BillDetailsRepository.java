package com.ElectricityBill.electricity_bill_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ElectricityBill.electricity_bill_project.entity.BillDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillDetailsRepository extends JpaRepository<BillDetails,Long>{

    @Query(value = "select b from BillDetails b where b.serviceRequestNo = :serviceRequestNo and paymentDone = :paymentDone")
	List<BillDetails> findBillDetails(@Param("serviceRequestNo") String serviceRequestNo, @Param("paymentDone") String paymentDone);
}
