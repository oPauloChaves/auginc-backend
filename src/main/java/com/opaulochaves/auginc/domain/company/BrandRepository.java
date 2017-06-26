package com.opaulochaves.auginc.domain.company;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
    List<Brand> findByNameLikeIgnoreCaseOrderByNameAsc(@Param("name") String name);
}
