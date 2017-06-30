package com.opaulochaves.auginc.domain.employee;

import com.opaulochaves.auginc.domain.common.EntryNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author Paulo Chaves
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public EmployeeDTO create(EmployeeRequestDTO newEmplyeeEntry) {
        LOG.info("Creating a new employee entry by using information: {}", newEmplyeeEntry);

        Employee created = new Employee();
        created.setEmail(newEmplyeeEntry.getEmail());
        created.setFirstName(newEmplyeeEntry.getFirstName());
        created.setLastName(newEmplyeeEntry.getLastName());
        created.setPassword(passwordEncoder.encode(newEmplyeeEntry.getPassword()));
        created.setPhoneNumber(newEmplyeeEntry.getPhoneNumber());
        created.setTitle(newEmplyeeEntry.getTitle());

        created = employeeRepository.save(created);
        LOG.info("Created a new employee entry: {}", created);

        return EmployeeMapper.mapEntityIntoDTO(created);
    }

    @Transactional
    @Override
    public EmployeeDTO delete(Long id) {
        LOG.info("Deleting a employee entry with id: {}", id);

        Employee deleted = findTodoEntryById(id);
        LOG.debug("Found employee entry: {}", deleted);
        deleted.setDeleted(true);
        employeeRepository.flush();

        LOG.info("Deleted employee entry: {}", deleted);
        return EmployeeMapper.mapEntityIntoDTO(deleted);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeDTO> findAll() {
        LOG.info("Finding all employee entries.");

        List<Employee> employeeEntries = employeeRepository.findAll();

        LOG.info("Found {} employee entries", employeeEntries.size());

        return EmployeeMapper.mapEntitiesIntoDTOs(employeeEntries);
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageRequest) {
        LOG.info("Finding all employee entries by page request: {}", pageRequest);
        
        Page<Employee> entries = employeeRepository.findAll(pageRequest);
        LOG.info("Found {} employee entries. Returned page {} contains {} employee entries",
                entries.getTotalElements(),
                entries.getNumber(),
                entries.getNumberOfElements()
        );
        
        return EmployeeMapper.mapEntityPageIntoDTOPage(pageRequest, entries);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeDTO findById(Long id) {
        LOG.info("Finding an employee entry with id: {}", id);

        Employee employeeEntry = findTodoEntryById(id);
        LOG.debug("Found employee entry: {}", employeeEntry);

        return EmployeeMapper.mapEntityIntoDTO(employeeEntry);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeDTO> findByIds(Iterable<Long> ids) {
        LOG.info("Finding a list of employees by ids: {}", ids);
        
        List<Employee> entries = employeeRepository.findAll(ids);
        LOG.debug("Found {} employee entries", entries.size());
        
        return EmployeeMapper.mapEntitiesIntoDTOs(entries);
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findEntityById(Long id) {
        LOG.info("Finding an employee entry with id: {}", id);

        Employee employeeEntry = findTodoEntryById(id);
        LOG.debug("Found employee entry: {}", employeeEntry);
        
        return employeeEntry;
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findByEmail(String email) {
        LOG.info("Finding an employee entry with email: {}", email);
        
        Employee employeeEntry = findTodoEntryByEmail(email);
        LOG.debug("Found employee entry: {}", employeeEntry);
        
        return employeeEntry;
    }

    @Transactional
    @Override
    public EmployeeDTO update(EmployeeEditDTO updatedEntry) {
        LOG.info("Updating the information of an employee entry by using information: {}", updatedEntry);

        Employee updated = findTodoEntryById(updatedEntry.getId());
        updated.setFirstName(updatedEntry.getFirstName());
        updated.setLastName(updatedEntry.getLastName());
        updated.setPhoneNumber(updatedEntry.getPhoneNumber());
        updated.setTitle(updatedEntry.getTitle());
        updated.setEnabled(updatedEntry.isEnabled());

        if (!StringUtils.isEmpty(updatedEntry.getPassword())) {
            updated.setPassword(passwordEncoder.encode(updatedEntry.getPassword()));
            LOG.info("Changing the employee #{} password", updatedEntry.getId());
        }

        //We need to flush the changes or otherwise the returned object
        //doesn't contain the updated audit information.
        employeeRepository.flush();

        LOG.info("Updated the information of the employee entry: {}", updated);

        return EmployeeMapper.mapEntityIntoDTO(updated);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EmployeeDTO> findBySearchTerm(String searchTerm, Pageable pageRequest) {
        LOG.info("Finding employee entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<Employee> searchResultPage = employeeRepository.findBySearchTerm(searchTerm, pageRequest);

        LOG.info("Found {} employee entries. Returned page {} contains {} employee entries",
                searchResultPage.getTotalElements(),
                searchResultPage.getNumber(),
                searchResultPage.getNumberOfElements()
        );

        return EmployeeMapper.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
    }

    private Employee findTodoEntryById(Long id) {
        Optional<Employee> todoResult = employeeRepository.findById(id);
        return todoResult.orElseThrow(() -> new EntryNotFoundException(id));
    }

    private Employee findTodoEntryByEmail(String email) {
        Optional<Employee> todoResult = employeeRepository.findByEmail(email);
        return todoResult.orElseThrow(() -> new EntryNotFoundException());
    }
}
