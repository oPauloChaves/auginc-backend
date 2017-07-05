package com.opaulochaves.auginc.domain.employee.customers;

import com.opaulochaves.auginc.domain.employee.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Paulo Chaves
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    Optional<Customer> findByIdAndEmployee_id(Long id, Long employeeID);

    List<Customer> findByEmployee(Employee employee);

    Page<Customer> findByEmployee_id(Long employeeID, Pageable pageRequest);

    List<Customer> findByEmployee_Email(String email);

    Optional<Customer> findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE"
            + " c.email like %:searchTerm% or c.firstName like %:searchTerm% or c.lastName like %:searchTerm%")
    Page<Customer> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);

    @Query("SELECT c FROM Customer c WHERE c.employee.id = :employeeID"
            + " and (c.email like %:searchTerm% or c.firstName like %:searchTerm% or c.lastName like %:searchTerm%)")
    Page<Customer> findEmployeesCustomersBySearchTerm(@Param("searchTerm") String searchTerm,
            @Param("employeeID") Long employeeID, Pageable pageRequest);
}
