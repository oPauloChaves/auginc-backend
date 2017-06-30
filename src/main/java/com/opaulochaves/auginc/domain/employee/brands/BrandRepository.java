package com.opaulochaves.auginc.domain.employee.brands;

import com.opaulochaves.auginc.domain.employee.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findById(Long id);
    
    List<Brand> findByNameLikeIgnoreCaseOrderByNameAsc(@Param("name") String name);

    /**
     * Finds all brands of a given employee
     *
     * @param employee
     * @return
     */
    List<Brand> findByEmployee(Employee employee);

    /**
     * Finds all brands of a given employee id
     *
     * @param employeeId
     * @return
     */
    Page<Brand> findByEmployee_id(Long employeeId, Pageable page);

    /**
     * Finds a brand by its given ID and ensure that this brand belongs to a
     * specific employee by also querying by his ID
     *
     * @param id
     * @param employeeID
     * @return
     */
    Optional<Brand> findByIdAndEmployee_id(Long id, Long employeeID);

    /**
     * Find all brands where its name contains the given search term
     *
     * @param searchTerm The given search term.
     * @param pageRequest The information of the requested page.
     * @return A page of employees brands entries whose names contains the given
     * search term. The content of the returned page depends from the page
     * request given as a method parameter.
     */
    @Query("SELECT b FROM Brand b WHERE b.name like %:searchTerm%")
    Page<Brand> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);

    /**
     * Find all brands represented by an employee where the brands name contains
     * the search term
     *
     * @param searchTerm The given search term.
     * @param employeeId The given employee ID
     * @param pageRequest The information of the requested page.
     * @return A page of employees brands entries whose names contains the given
     * search term. The content of the returned page depends from the page
     * request given as a method parameter.
     */
    @Query("SELECT b FROM Brand b WHERE b.employee.id = :employeeId and b.name like %:searchTerm%")
    Page<Brand> findEmployeesBrandsBySearchTerm(@Param("searchTerm") String searchTerm,
            @Param("employeeId") Long employeeId, Pageable pageRequest);
}
