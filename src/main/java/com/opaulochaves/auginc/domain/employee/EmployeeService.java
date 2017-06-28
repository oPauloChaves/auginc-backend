package com.opaulochaves.auginc.domain.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author paulo
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService() {
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findOne(id);
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setPhoneNumber(employee.getPhoneNumber());

        return employeeRepository.save(existingEmployee);
    }
    
    @Transactional(readOnly = true)
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
    
    public void delete(Long id) {
        Employee existingEmployee = employeeRepository.findOne(id);
        existingEmployee.setDeleted(true);
        employeeRepository.save(existingEmployee);
    }
}
