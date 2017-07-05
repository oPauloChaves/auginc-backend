package com.opaulochaves.auginc.domain.employee.customers;

import com.opaulochaves.auginc.domain.common.EntryNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Paulo Chaves
 */
public interface CustomerService {

    /**
     * Creates a new customer entry.
     *
     * @param newEntry The information of the created customer entry.
     * @return The information of the created customer entry.
     */
    CustomerDTO create(CustomerDTO newEntry);

    /**
     * Deletes a customer entry from the database.<br><br>
     *
     * This method is intended to be used by an ADMIN
     *
     * @param id The id of the deleted customer entry.
     * @throws EntryNotFoundException if the deleted customer entry is not
     * found.
     */
    void delete(Long id);

    /**
     * Deletes a customer entry of a given employee ID from the
     * database.<br><br>
     *
     * This method is intended to be used by a MANAGER or ADMIN
     *
     * @param id The id of the deleted customer entry.
     * @param employeeID The id of the employee
     * @throws EntryNotFoundException if the deleted customer entry is not
     * found.
     */
    void delete(Long id, Long employeeID);

    /**
     * Deletes all customers of a given employee ID from the database.<br><br>
     *
     * TODO: Maybe it doesn't make sense to delete a customer since it can have
     * lots of relationship with others entities and this action could break the
     * application<br><br>
     *
     * This method is intended to be used by a MANAGER
     *
     * @param employeeID The id of an employee
     */
    void deleteEmployeesCustomers(Long employeeID);

    /**
     * Find all customers entry
     *
     * @return
     */
    List<CustomerDTO> findAll();

    /**
     * Find all customers entry
     *
     * @return
     */
    Page<CustomerDTO> findAll(Pageable pageRequest);

    /**
     * Find all customers entry of a given employee
     *
     * @param employeeID
     * @return
     */
    Page<CustomerDTO> findByEmployeeID(Long employeeID, Pageable pageRequest);

    /**
     * Find all customers entry of a given employee
     *
     * @param employeeEmail
     * @return
     */
    List<CustomerDTO> findByEmployeeEmail(String employeeEmail);

    /**
     * Find a customer given its ID
     *
     * @param id
     * @return
     * @throws EntryNotFoundException if the customer entry is not found.
     */
    CustomerDTO findByID(Long id);

    /**
     * Find a list of customers by their ids
     *
     * @param ids
     * @return
     */
    List<CustomerDTO> findByIds(Iterable<Long> ids);

    /**
     * Find a customer entry of a specific employee given the customers ID and
     * employee's ID
     *
     * @param id
     * @param employeeID
     * @return
     * @throws EntryNotFoundException if the customer entry is not found.
     */
    CustomerDTO findCustomersEmployeeByID(Long id, Long employeeID);

    /**
     * Update an existing customer of a specific employee
     *
     * @param updatedEntry
     * @return
     */
    CustomerDTO update(CustomerDTO updatedEntry);

    /**
     * Finds customers entries whose name or email contains the given search
     * term. This search is case insensitive.
     *
     * @param searchTerm The search term.
     * @param pageRequest The information of the requested page.
     * @return A list of customers entries whose name contains the given search
     * term. The returned list is sorted by using the sort specification given
     * as a method parameter.
     */
    Page<CustomerDTO> findBySearchTerm(String searchTerm, Pageable pageRequest);

    /**
     * Finds customers entries of a specific employee whose customers name or
     * email contains the given search term. This search is case insensitive.
     *
     * @param searchTerm The search term.
     * @param employeeID
     * @param pageRequest The information of the requested page.
     * @return A list of customers entries whose name contains the given search
     * term. The returned list is sorted by using the sort specification given
     * as a method parameter.
     */
    Page<CustomerDTO> findEmployeesCustomersBySearchTerm(String searchTerm, Long employeeID, Pageable pageRequest);
}
