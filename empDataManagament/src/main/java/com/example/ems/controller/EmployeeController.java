package com.example.ems.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.service.EmployeeService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	//private EmployeeService employeeService;
	private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
	//Build Add Employee Rest API
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeDto){
		EmployeeDto savedEmployee = employeeService.createEmployee(employeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
		
	}
	//Build Get Employee Rest API
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id")Long employeeId){
		EmployeeDto employeeDto=employeeService.getEmployeeById(employeeId);
		System.out.println(employeeDto);
		return ResponseEntity.ok(employeeDto);
		
	}
	
	//Build Get All Employees RestAPI
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
		
	}
	//Build Update Employee RestA
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id")  Long employeeId, 
			@RequestBody EmployeeDto updateEmployeeDto){
		EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployeeDto);
		
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee deleted Successfully!.");
	}
	
	
	
	
}
