package com.example.ems.service.impl;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.ems.dto.EmployeeDto;
import com.example.ems.entity.Employee;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	private EmployeeRepository employeeRepository;
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee= employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}
	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		        .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given Id:" + employeeId));
	return EmployeeMapper.mapToEmployeeDto(employee);
		
	}
	
	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}
	
	@Override
	public EmployeeDto updateEmployee(Long employeeId,EmployeeDto updateEmployee) {
		 
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with given Id:" + employeeId));
		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());
		Employee updateEmployee1=employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(updateEmployee1);
	}
	@Override
	public void deleteEmployee(Long employeeId) {
	    if (employeeId == null) {
	        throw new IllegalArgumentException("Employee ID must not be null");
	    }

	    Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given ID: " + employeeId));

	    employeeRepository.deleteById(employeeId);
	}

	

}
