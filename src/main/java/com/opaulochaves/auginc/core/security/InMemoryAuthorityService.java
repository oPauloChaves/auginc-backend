package com.opaulochaves.auginc.core.security;

import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Profile("!oauth")
public class InMemoryAuthorityService implements AuthorityService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Collection<String> getUserEmailsByAuthority(String authority) {
        return employeeRepository.findAllForAddressBook(new PageRequest(0, 100))
                .getContent()
                .stream()
                .map(Employee::getEmail)
                .collect(Collectors.toList());
    }

}
