package com.opaulochaves.auginc.web;

import com.opaulochaves.auginc.domain.employee.EmployeeDTO;
import com.opaulochaves.auginc.domain.employee.EmployeeNotFoundException;
import com.opaulochaves.auginc.domain.employee.EmployeeRequestDTO;
import com.opaulochaves.auginc.domain.employee.EmployeeService;
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
 * @author paulo
 */
@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Finds employee entries whose first name or last name contains the given
     * search term. This search is case insensitive.
     *
     * @param searchTerm The used search term.
     * @param pageRequest The information of the requested page.
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<EmployeeDTO> findBySearchTerm(@RequestParam("q") String searchTerm, Pageable pageRequest) {
        LOG.info("Finding employee entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<EmployeeDTO> searchResult = employeeService.findBySearchTerm(searchTerm, pageRequest);
        LOG.info("Found {} employee entries. Returned page {} contains {} employee entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return searchResult;
    }

    /**
     * Create a new employee entry.
     *
     * @param newEntry The information of the created employee entry.
     * @return The information of the created employee entry.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    EmployeeDTO create(@RequestBody @Valid EmployeeRequestDTO newEntry) {
        LOG.info("Creating a new employee entry by using information: {}", newEntry);

        EmployeeDTO created = employeeService.create(newEntry);
        LOG.info("Created a new employee entry: {}", created);

        return created;
    }

    /**
     * Deletes an employee entry. For this application deleting an employee means
     * setting the <b>deleted</b> field to {@literal false}
     *
     * @param id The id of the deleted employee entry.
     * @return The information of the deleted employee entry.
     * @throws EmployeeNotFoundException if the deleted employee entry is not
     * found.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeDTO delete(@PathVariable("id") Long id) {
        LOG.info("Deleting a todo entry with id: {}", id);

        EmployeeDTO deleted = employeeService.delete(id);
        LOG.info("Deleted the todo entry: {}", deleted);

        return deleted;
    }

    /**
     * Finds all employee entries.
     *
     * @return The information of all employee entries.
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    List<EmployeeDTO> findAll() {
        LOG.info("Finding all employee entries");

        List<EmployeeDTO> employeeEntries = employeeService.findAll();
        LOG.info("Found {} employee entries.", employeeEntries.size());

        return employeeEntries;
    }

    /**
     * Finds a single employee entry.
     *
     * @param id The id of the requested employee entry.
     * @return The information of the requested employee entry.
     * @throws EmployeeNotFoundException if no employee entry is found by using
     * the given id.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    EmployeeDTO findById(@PathVariable("id") Long id) {
        LOG.info("Finding employee employee by using id: {}", id);

        EmployeeDTO todoEntry = employeeService.findById(id);
        LOG.info("Found employee entry: {}", todoEntry);

        return todoEntry;
    }

    /**
     * Updates the information of an existing employee entry.
     *
     * @param updatedEntry The new information of the updated employee entry.
     * @return The updated information of the updated employee entry.
     * @throws EmployeeNotFoundException if no employee entry is found by using
     * the given id.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    EmployeeDTO update(@RequestBody @Valid EmployeeRequestDTO updatedEntry) {
        LOG.info("Updating an employee entry by using information: {}", updatedEntry);

        EmployeeDTO updated = employeeService.update(updatedEntry);
        LOG.info("Updated the employee entry: {}", updated);

        return updated;
    }
}
