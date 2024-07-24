package com.ElectricityBill.electricity_bill_project.service;

import com.ElectricityBill.electricity_bill_project.entity.BillDetails;
import com.ElectricityBill.electricity_bill_project.entity.GenerateBillRequest;
import org.springframework.stereotype.Service;

import com.ElectricityBill.electricity_bill_project.repository.BillDetailsRepository;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class BillDetailsService {

	private BillDetailsRepository billDetailsRepository;

	public List<BillDetails> getBillDetails(String serviceRequestNo){

		return billDetailsRepository.findBillDetails(serviceRequestNo, "No");
	}
	public String doPayment(Long id, String amount){
		BillDetails billDetails = billDetailsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Bill Details does not exist"));
		billDetails.setBillPayment(amount);
		billDetails.setPaymentDate(LocalDate.now());
		billDetails.setPaymentDone("Yes");

		billDetailsRepository.save(billDetails);
		return "Bill Details is done successfully";
	}

	public String generateBill(GenerateBillRequest request){
		int totalUnit = request.getTotalUnit();
		double totalBill = 0;
		if (totalUnit < 100){
			totalBill = totalUnit * 7;
		} else if (totalUnit >= 100 && totalUnit < 500) {
			totalBill = totalUnit * 9;
		} else if (totalUnit >= 500) {
			totalBill = totalUnit * 12;
		}

		Random random = new Random();
		double perMonthBill = totalBill /3;
		LocalDate billDueDate = LocalDate.now().plusMonths(1).withDayOfMonth(1);
		List<BillDetails> billDetails = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			BillDetails billDetail = new BillDetails();
			billDetail.setBillAmount(perMonthBill);
			billDetail.setBillAmountAfterDueDate(perMonthBill + 10);
			billDetail.setBillCreationDate(LocalDate.now());
			billDetail.setBillDueDate(billDueDate);
			billDetail.setBillNo("B_" + random.nextInt());
			billDetail.setServiceRequestNo(request.getServiceRequestNo());
			billDetail.setPaymentDone("No");

			billDueDate = billDueDate.plusMonths(1);
			billDetails.add(billDetail);
		}
		billDetailsRepository.saveAll(billDetails);
		return "Bill generation is done successfully";
	}
	
}
