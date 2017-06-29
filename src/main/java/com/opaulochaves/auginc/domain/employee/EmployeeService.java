package com.opaulochaves.auginc.domain.employee;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This service provides CRUD operations and finder methods for {@link Employee}
 * objects.
 *
 * @author Paulo Chaves
 */
public interface EmployeeService {

    /**
     * Creates a new employee entry.
     *
     * @param newEmplyeeEntry The information of the created employee entry.
     * @return The information of the created employee entry.
     */
    EmployeeDTO create(EmployeeRequestDTO newEmplyeeEntry);

    /**
     * Deletes an employee entry from the database.
     *
     * @param id The id of the deleted employee entry.
     * @return The information of the deleted employee entry.
     * @throws EmployeeNotFoundException if the deleted employee entry is not
     * found.
     */
    EmployeeDTO delete(Long id);

    /**
     * Finds all employee entries that are saved to the database.
     *
     * @return
     */
    List<EmployeeDTO> findAll();

    /**
     * Finds an employee entry by using the id given as a method parameter. It
     * returns a DTO from the retrieved entity
     *
     * @param id The id of the wanted employee entry.
     * @return The information of the requested employee entry.
     * @throws EmployeeNotFoundException if no employee entry is found with the
     * given id.
     */
    EmployeeDTO findById(Long id);

    /**
     * Finds an employee entry by using the id given as a method parameter. It
     * returns an entity employee from the database
     *
     * @param id The id of the wanted employee entry.
     * @return The information of the requested employee entry.
     * @throws EmployeeNotFoundException if no employee entry is found with the
     * given id.
     */
    Employee findEntityById(Long id);

    /**
     * Finds an employee entry by using the email given as a method parameter.
     *
     * @param email The email of the wanted employee entry.
     * @return The information of the requested employee entry.
     * @throws EmployeeNotFoundException if no employee entry is found with the
     * given email.
     */
    Employee findByEmail(String email);

    /**
     * Updates the information of an existing employee.
     *
     * @param updatedEmployeeEntry The new information of an existing employee
     * entry.
     * @return The information of the updated employee entry.
     * @throws EmployeeNotFoundException if no employee entry is found with the
     * given id.
     */
    EmployeeDTO update(EmployeeEditDTO updatedEmployeeEntry);

    /**
     * Finds employee entries whose first name or last name contains the given
     * search term. This search is case insensitive.
     *
     * @param searchTerm The search term.
     * @param pageRequest The information of the requested page.
     * @return A list of employee entries whose title or description contains
     * the given search term. The returned list is sorted by using the sort
     * specification given as a method parameter.
     */
    Page<EmployeeDTO> findBySearchTerm(String searchTerm, Pageable pageRequest);
}
