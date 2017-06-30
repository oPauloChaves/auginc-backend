package com.opaulochaves.auginc.domain.employee.brands;

import com.opaulochaves.auginc.domain.common.EntryNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This service provides CRUD operations and finder methods for {@link Brand}
 * objects.
 *
 * @author Paulo Chaves
 */
public interface BrandService {

    /**
     * Creates a new brand entry.
     *
     * @param newEntry The information of the created brand entry.
     * @return The information of the created brand entry.
     */
    BrandDTO create(BrandDTO newEntry);

    /**
     * Deletes a brand entry from the database.<br><br>
     *
     * This method is intended to be used by an ADMIN
     *
     * @param id The id of the deleted brand entry.
     * @throws EntryNotFoundException if the deleted brand entry is not found.
     */
    void delete(Long id);

    /**
     * Deletes a brand entry of a given employee ID from the database.<br><br>
     *
     * This method is intended to be used by a MANAGER or ADMIN
     *
     * @param id The id of the deleted brand entry.
     * @param employeeID The id of the employee
     * @throws EntryNotFoundException if the deleted brand entry is not found.
     */
    void delete(Long id, Long employeeID);

    /**
     * Deletes all brands of a given employee ID from the database.<br><br>
     *
     * TODO: Maybe it doesn't make sense to delete a brand since it can have
     * lots of relationship with others entities and this action could break the
     * application<br><br>
     *
     * This method is intended to be used by a MANAGER
     *
     * @param employeeID The id of an employee
     */
    void deleteEmployeesBrands(Long employeeID);

    /**
     * Find all brands entry
     *
     * @return
     */
    List<BrandDTO> findAll();

    /**
     * Find all brands entry
     *
     * @return
     */
    Page<BrandDTO> findAll(Pageable pageRequest);

    /**
     * Find all brands entry of a given employee
     *
     * @param employeeID
     * @return
     */
    List<BrandDTO> findByEmployeeID(Long employeeID);

    /**
     * Find all brands entry of a given employee
     *
     * @param employeeEmail
     * @return
     */
    List<BrandDTO> findByEmployeeEmail(String employeeEmail);

    /**
     * Find a brand given its ID
     *
     * @param id
     * @return
     * @throws EntryNotFoundException if the brand entry is not found.
     */
    BrandDTO findByID(Long id);

    /**
     * Find brands by their ids
     *
     * @param ids
     * @return
     */
    List<BrandDTO> findByIds(Iterable<Long> ids);

    /**
     * Find a brand entry of a specific employee given the brands ID and
     * employee's ID
     *
     * @param id
     * @param employeeID
     * @return
     * @throws EntryNotFoundException if the brand entry is not found.
     */
    BrandDTO findBrandsEmployeeByID(Long id, Long employeeID);

    /**
     * Update an existing brand of a specific employee
     *
     * @param updatedEntry
     * @return
     */
    BrandDTO update(BrandDTO updatedEntry);

    /**
     * Finds brands entries whose name contains the given search term. This
     * search is case insensitive.
     *
     * @param searchTerm The search term.
     * @param pageRequest The information of the requested page.
     * @return A list of brands entries whose name contains the given search
     * term. The returned list is sorted by using the sort specification given
     * as a method parameter.
     */
    Page<BrandDTO> findBySearchTerm(String searchTerm, Pageable pageRequest);

    /**
     * Finds brands entries of a specific employee whose brands name contains
     * the given search term. This search is case insensitive.
     *
     * @param searchTerm The search term.
     * @param employeeID
     * @param pageRequest The information of the requested page.
     * @return A list of brands entries whose name contains the given search
     * term. The returned list is sorted by using the sort specification given
     * as a method parameter.
     */
    Page<BrandDTO> findEmployeesBrandsBySearchTerm(String searchTerm, Long employeeID, Pageable pageRequest);
}
