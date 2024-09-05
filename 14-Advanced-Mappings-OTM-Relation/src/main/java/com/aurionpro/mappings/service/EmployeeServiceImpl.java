package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.EmployeeDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.repository.ClientRepository; // Import the ClientRepository
import com.aurionpro.mappings.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private ClientRepository clientRepo;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = toEmployeeMapper(employeeDto);
        employee = employeeRepo.save(employee);
        return toEmployeeDtoMapper(employee);
    }

    @Override
    public PageResponseDto<EmployeeDto> getAllEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employeePage = employeeRepo.findAll(pageable);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeePage.getContent()) {
            employeeDtoList.add(toEmployeeDtoMapper(employee));
        }

        PageResponseDto<EmployeeDto> employeePageDto = new PageResponseDto<>();
        employeePageDto.setTotalPages(employeePage.getTotalPages());
        employeePageDto.setTotalElements(employeePage.getTotalElements());
        employeePageDto.setSize(employeePage.getSize());
        employeePageDto.setContents(employeeDtoList);
        employeePageDto.setLastPage(employeePage.isLast());

        return employeePageDto;
    }

    @Override
    public EmployeeDto getEmployeeById(int employeeId) {
        Optional<Employee> employeeData = employeeRepo.findById(employeeId);
        if (employeeData.isEmpty()) {
            return null;
        }
        Employee dbEmployee = employeeData.get();
        return toEmployeeDtoMapper(dbEmployee);
    }

    private EmployeeDto toEmployeeDtoMapper(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setHireDate(employee.getHireDate());
        employeeDto.setSalary(employee.getSalary());
//        employeeDto.setAccountNumber(employee.getAccountNumber());
        employeeDto.setStatus(employee.getStatus());
        employeeDto.setClientId(employee.getClient().getClientId());
        return employeeDto;
    }

    private Employee toEmployeeMapper(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmail(employeeDto.getEmail());
        employee.setPosition(employeeDto.getPosition());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setSalary(employeeDto.getSalary());
//        employee.setAccountNumber(employeeDto.getAccountNumber());
        employee.setStatus(employeeDto.getStatus());
        Optional<Client> clientOpt = clientRepo.findById(employeeDto.getClientId());
        if (clientOpt.isPresent()) {
            employee.setClient(clientOpt.get());
        }

        return employee;
    }
}
