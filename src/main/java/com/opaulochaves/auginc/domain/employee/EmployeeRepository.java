package com.opaulochaves.auginc.domain.employee;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    
    /**
     * This query method invokes the JPQL query that is configured by using the {@code @Query} annotation.
     * @param searchTerm    The given search term.
     * @param pageRequest   The information of the requested page.
     * @return  A page of employee entries whose firstName or lastName contains with the given search term. The content of
     *          the returned page depends from the page request given as a method parameter.
     */
    @Query("SELECT e FROM Employee e WHERE"
            + " e.email like %:searchTerm%"
            + " or e.firstName like %:searchTerm%"
            + " or e.lastName like %:searchTerm%")
    Page<Employee> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);
    
    Optional<Employee> findById(Long id);
}
