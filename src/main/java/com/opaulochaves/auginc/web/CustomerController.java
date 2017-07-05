package com.opaulochaves.auginc.web;

import com.opaulochaves.auginc.domain.employee.customers.CustomerDTO;
import com.opaulochaves.auginc.domain.employee.customers.CustomerService;
import com.opaulochaves.auginc.web.util.ListIds;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Paulo Chaves
 */
@RestController
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Finds customers entries whose name contains the given search term. This
     * search is case insensitive.
     *
     * @param searchTerm The used search term.
     * @param pageRequest The information of the requested page.
     * @return
     */
    @RequestMapping(value = "/api/customers/search", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<CustomerDTO> findBySearchTerm(@RequestParam("q") String searchTerm, Pageable pageRequest) {
        LOG.debug("Finding customer entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<CustomerDTO> searchResult = customerService.findBySearchTerm(searchTerm, pageRequest);
        LOG.debug("Found {} todo entries. Returned page {} contains {} todo entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return searchResult;
    }

    /**
     * Find customers entries of an employee whose customer name contains the
     * search term. The search is case insensitive
     *
     * @param employeeID The employee ID
     * @param searchTerm The used search term
     * @param pageRequest The information of the page requested
     * @return
     */
    @RequestMapping(value = "/api/employees/{employeeID}/customers/search", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public Page<CustomerDTO> findEmployeesCustomersBySearchTerm(@PathVariable("employeeID") Long employeeID,
            @RequestParam("q") String searchTerm, Pageable pageRequest) {
        LOG.debug("Finding employees customer entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<CustomerDTO> searchResult = customerService.findEmployeesCustomersBySearchTerm(searchTerm, employeeID, pageRequest);
        LOG.debug("Found {} todo entries. Returned page {} contains {} todo entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return searchResult;
    }

    /**
     * Create a new customer entry.
     *
     * @param employeeID The employee ID
     * @param newEntry The information of the created customer entry.
     * @return The information of the created customer entry.
     */
    @RequestMapping(value = "/api/employees/{employeeID}/customers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public CustomerDTO create(@PathVariable("employeeID") Long employeeID, @RequestBody @Valid CustomerDTO newEntry) {
        newEntry.setEmployeeID(employeeID);
        LOG.debug("Creating a new customer entry by using information: {}", newEntry);

        CustomerDTO created = customerService.create(newEntry);
        LOG.debug("Created a new customer entry: {}", created);

        return created;
    }

    /**
     * Update a customer entry.
     *
     * @param employeeID The employee ID
     * @param customerID
     * @param updatedEntry The information of the existing customer entry.
     * @return The information of the updated customer entry.
     */
    @RequestMapping(value = "/api/employees/{employeeID}/customers/{customerID}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public CustomerDTO update(@PathVariable Long employeeID, @PathVariable Long customerID, 
            @RequestBody @Valid CustomerDTO updatedEntry) {
        updatedEntry.setId(customerID);
        updatedEntry.setEmployeeID(employeeID);
        LOG.debug("Updating customer entry by using information: {}", updatedEntry);

        CustomerDTO updated = customerService.update(updatedEntry);
        LOG.debug("Updated a customer entry: {}", updated);

        return updated;
    }

    /**
     * Finds all customer entries.
     *
     * @param pageRequest
     * @return The information of all customer entries.
     */
    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<CustomerDTO> findAll(Pageable pageRequest) {
        LOG.debug("Finding all customer entries");

        Page<CustomerDTO> entries = customerService.findAll(pageRequest);
        LOG.debug("Found {} todo entries. Returned page {} contains {} todo entries",
                entries.getTotalElements(),
                entries.getNumber(),
                entries.getNumberOfElements()
        );

        return entries;
    }

    /**
     * Finds all customer entries of an employee.
     *
     * @param employeeID The employee ID
     * @return The information of all customer entries of an employee
     */
    @RequestMapping(value = "/api/employees/{employeeID}/customers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public List<CustomerDTO> findByEmployeeID(@PathVariable Long employeeID) {
        LOG.debug("Finding all customer entries of an employee");

        List<CustomerDTO> entries = customerService.findByEmployeeID(employeeID);
        LOG.debug("Found {} customer entries.", entries.size());

        return entries;
    }

    /**
     * Finds a single customer entry
     *
     * @param id
     * @return The information of a single customer entry
     */
    @RequestMapping(value = "/api/customers/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerDTO findById(@PathVariable Long id) {
        LOG.debug("Finding customer by using id: {}", id);

        CustomerDTO entry = customerService.findByID(id);
        LOG.debug("Found customer entry: {}", entry);

        return entry;
    }
    
    @RequestMapping(value = "/api/customers/list", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerDTO> findByIds(ListIds ids) {
        LOG.debug("Finding customer by their ids: {}", ids.getIds());
        
        List<CustomerDTO> entries = customerService.findByIds(ids.getIds());
        LOG.debug("Found {} customer entries", entries.size());
        
        return entries;
    }

    /**
     * Finds a single customer of an employee.
     *
     * @param id The customer ID
     * @param employeeID The employee ID
     * @return The information of a single customer of an employee
     */
    @RequestMapping(value = "/api/employees/{employeeID}/customers/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public CustomerDTO findCustomersEmployeeByID(@PathVariable Long id, @PathVariable Long employeeID) {
        LOG.debug("Finding customer by using id: {}", id);

        CustomerDTO entry = customerService.findCustomersEmployeeByID(id, employeeID);
        LOG.debug("Found customer entry: {}", entry);

        return entry;
    }
}
