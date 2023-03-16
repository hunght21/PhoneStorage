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

import com.ltnc.phonestorage.entity.Employee;
import com.ltnc.phonestorage.service.EmployeeService;



@RestController
@RequestMapping("/api/employees")
public class RestEmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    public void EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

    @GetMapping("/all")
	public ResponseEntity<?> getAllEmployees(){
		ResponseEntity<?> resp;
		try {
			List<Employee> list = employeeService.getAllEmployees();
			if(list!=null && !list.isEmpty()) {
				resp = new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("No Record Found",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

    @GetMapping("/one/{id}")
	public ResponseEntity<?> fetchOneEmployees(@PathVariable("id") Integer id){
		ResponseEntity<?> resp;
		try {
			Employee employee = employeeService.getEmployeeById(id);
			if(employee!=null) {
				resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>("No Data Found",(HttpStatus.NO_CONTENT));
			}
		}catch(Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveEmployees(@RequestBody Employee employee){

		ResponseEntity<?> resp;
		employeeService.saveEmployee(employee);
		try {
			resp =  new ResponseEntity<String>("EmployeeId regiestered with "+employee.getEmployeeId(),HttpStatus.CREATED);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer employessId){
		ResponseEntity<?> resp=null;
		try {
			employeeService.deleteEmployeeById(employessId);
			resp = new ResponseEntity<String>("Employess "+employessId+" deleted",HttpStatus.OK);

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;

	}
}
