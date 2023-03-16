package com.ltnc.phonestorage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltnc.phonestorage.entity.Customer;
import com.ltnc.phonestorage.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class RestCustomerController {
    
    @Autowired
    private CustomerService customerService;


    @GetMapping("/all")
	public ResponseEntity<?> getAllCustomers(){
		ResponseEntity<?> resp;
		try {
			List<Customer> list = customerService.getAllCustomers();
			if(list!=null && !list.isEmpty()) {
				resp = new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("No Record Found",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

    @GetMapping("/one/{id}")
	public ResponseEntity<?> fetchOneCustomers(@PathVariable("id") Integer id){
		ResponseEntity<?> resp;
		try {
			Customer customer = customerService.getCustomerById(id);
			if(customer!=null) {
				resp = new ResponseEntity<Customer>(customer, HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("No Data Found",(HttpStatus.NO_CONTENT));
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){

		ResponseEntity<?> resp;
		customerService.saveCustomer(customer);
		try {
			resp =  new ResponseEntity<String>("Customers regiestered with "+customer.getCustomerId(),HttpStatus.CREATED);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer customerId){
		ResponseEntity<?> resp=null;
		try {
			customerService.deleteCustomerById(customerId);
			resp = new ResponseEntity<String>("Customers "+customerId+" deleted",HttpStatus.OK);

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;

	}
}
