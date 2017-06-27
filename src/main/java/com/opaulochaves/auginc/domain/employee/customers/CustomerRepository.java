package com.opaulochaves.auginc.domain.employee.customers;

import com.opaulochaves.auginc.domain.employee.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author paulo
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // TODO override methods, set authorization and create Method Security Meta Annotations
    // to avoid write the same expression over and over
    @Override
    @PostAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and returnObject?.employee.email == principal?.username)")
    Customer findOne(@Param("id") Long id);

    @PreAuthorize("hasRole('MANAGER') or #employee?.email == principal?.username")
    List<Customer> findByEmployee(Employee employee);

    List<Customer> findByEmployee_Email(String email);

    Customer findByEmail(String email);
}
