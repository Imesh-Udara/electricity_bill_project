package com.ElectricityBill.electricity_bill_project.controller;

import com.ElectricityBill.electricity_bill_project.entity.BillDetails;
import com.ElectricityBill.electricity_bill_project.entity.GenerateBillRequest;
import com.ElectricityBill.electricity_bill_project.service.BillDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BillDetailsController {
	private BillDetailsService billDetailsService;

    @GetMapping(path = "/getBillDetails")
    public ResponseEntity<List<BillDetails>> getBillDetails(@RequestParam String serviceRequestNo){
        return ResponseEntity.ok(billDetailsService.getBillDetails(serviceRequestNo));
    }

    @PutMapping(path = "/doPayment")
    public ResponseEntity<String> doPayment(@RequestParam Long id, @RequestParam String amount){
        return new ResponseEntity<>(billDetailsService.doPayment(id,amount), HttpStatus.OK);
    }

    @PostMapping(path = "/generateBill")
    public ResponseEntity<String> generateBill(@RequestBody GenerateBillRequest request){
        return new ResponseEntity<>(billDetailsService.generateBill(request), HttpStatus.OK);
    }
}
