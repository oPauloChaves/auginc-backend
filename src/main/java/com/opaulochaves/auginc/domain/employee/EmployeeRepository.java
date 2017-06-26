package com.opaulochaves.auginc.domain.employee;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    @PostAuthorize("hasRole('ROLE_MANAGER') or returnObject?.email == principal?.username")
    Employee findOne(@Param("id") Long id);

    @Override
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    List<Employee> findAll();

    @Override
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    List<Employee> findAll(Sort sort);

    @Override
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    List<Employee> findAll(Iterable<Long> longs);

    @Override
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    Page<Employee> findAll(Pageable pageable);

    /**
     * Not exported find all method to access all employees without the need for
     * MANAGER/ADMIN rights.
     *
     * Filters the admin out. Only used for the address book.
     */
    @RestResource(exported = false)
    @Query("select e from Employee e where e.email <> 'admin@auginc.com' and e.deleted = false")
    Page<Employee> findAllForAddressBook(Pageable pageable);

    @RestResource(exported = false)
    Employee findByEmail(String email);
}
