package com.opaulochaves.auginc.domain.employee.customers;

import com.opaulochaves.auginc.domain.common.EntryNotFoundException;
import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Paulo Chaves
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeService employeeService;

    @Transactional
    @Override
    public CustomerDTO create(CustomerDTO newEntry) {
        LOG.info("Creating a new customer entry from: {}", newEntry);

        Employee employee = employeeService.findEntityById(newEntry.getEmployeeID());
        Customer created = new Customer();
        created.setEmployee(employee);
        created.setFirstName(newEntry.getFirstName());
        created.setLastName(newEntry.getLastName());
        created.setEmail(newEntry.getEmail());
        created.setPhoneNumber(newEntry.getPhoneNumber());
        created.setCellNumber(newEntry.getCellNumber());
        created.setNote(newEntry.getNote());
        created.setAddress(newEntry.getAddress());

        created = customerRepository.save(created);

        return CustomerMapper.mapEntityIntoDTO(created);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id, Long employeeID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEmployeesCustomers(Long employeeID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAll() {
        LOG.info("Finding all customer entries.");

        List<Customer> allEntries = customerRepository.findAll();
        LOG.info("Found {} customer entries", allEntries.size());

        return CustomerMapper.mapEntitiesIntoDTOs(allEntries);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Page<CustomerDTO> findAll(Pageable pageRequest) {
        LOG.info("Finding all customer entries.");

        Page<Customer> pageResult = customerRepository.findAll(pageRequest);
        LOG.info("Found {} customer entries. Returned page {} contains {} customer entries",
                pageResult.getTotalElements(),
                pageResult.getNumber(),
                pageResult.getNumberOfElements()
        );

        return CustomerMapper.mapEntityPageIntoDTOPage(pageRequest, pageResult);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findByEmployeeID(Long employeeID) {
        LOG.info("Finding all customer entries of an employee ID.");

        List<Customer> allEntries = customerRepository.findByEmployee_id(employeeID);
        LOG.info("Found {} customer entries for the employee #{}", allEntries.size(), employeeID);

        return CustomerMapper.mapEntitiesIntoDTOs(allEntries);
    }

    @Override
    public List<CustomerDTO> findByEmployeeEmail(String employeeEmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findByID(Long id) {
        LOG.info("Finding a customer entry with id: {}", id);

        Customer entry = findEntryById(id, null);
        LOG.info("Found customer entry: {}", entry);

        return CustomerMapper.mapEntityIntoDTO(entry);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findByIds(Iterable<Long> ids) {
        LOG.info("Finding customer by their ids: {}", ids);
        
        List<Customer> entries = customerRepository.findAll(ids);
        LOG.debug("Found {} customers entries", entries.size());
        
        return CustomerMapper.mapEntitiesIntoDTOs(entries);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomersEmployeeByID(Long id, Long employeeID) {
        LOG.info("Finding a customer entry with id {} of an employee with id {}", id, employeeID);

        Customer entry = findEntryById(id, employeeID);
        LOG.info("Found a customer {}", entry);

        return CustomerMapper.mapEntityIntoDTO(entry);
    }

    @Transactional
    @Override
    public CustomerDTO update(CustomerDTO updatedEntry) {
        LOG.info("Updating a customer entry by using information: {}", updatedEntry);

        Customer updated = findEntryById(updatedEntry.getId(), updatedEntry.getEmployeeID());
        updated.setFirstName(updatedEntry.getFirstName());
        updated.setLastName(updatedEntry.getLastName());
        updated.setEmail(updatedEntry.getEmail());
        updated.setPhoneNumber(updatedEntry.getPhoneNumber());
        updated.setCellNumber(updatedEntry.getCellNumber());
        updated.setNote(updatedEntry.getNote());
        updated.setAddress(updatedEntry.getAddress());

        customerRepository.flush();

        LOG.info("Updated the customer entry: {}", updated);

        return CustomerMapper.mapEntityIntoDTO(updated);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CustomerDTO> findBySearchTerm(String searchTerm, Pageable pageRequest) {
        LOG.info("Finding customer entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<Customer> searchResult = customerRepository.findBySearchTerm(searchTerm, pageRequest);

        LOG.info("Found {} customer entries. Returned page {} contains {} customer entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return CustomerMapper.mapEntityPageIntoDTOPage(pageRequest, searchResult);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CustomerDTO> findEmployeesCustomersBySearchTerm(String searchTerm, Long employeeID, Pageable pageRequest) {
        LOG.info("Finding customer entries by search term: {} and page request: {} and employee: {}", searchTerm, pageRequest, employeeID);

        Page<Customer> searchResult = customerRepository.findEmployeesCustomersBySearchTerm(searchTerm, employeeID, pageRequest);

        LOG.info("Found {} customer entries. Returned page {} contains {} customer entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return CustomerMapper.mapEntityPageIntoDTOPage(pageRequest, searchResult);
    }

    private Customer findEntryById(Long id, Long employeeID) {
        Optional<Customer> entry;

        if (employeeID != null) {
            entry = customerRepository.findByIdAndEmployee_id(id, employeeID);
        } else {
            entry = customerRepository.findById(id);
        }

        return entry.orElseThrow(() -> new EntryNotFoundException(id));
    }
}
