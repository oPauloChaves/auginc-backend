package com.opaulochaves.auginc.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource( collectionResourceRel = "companies", path = "companies" )
public interface CompanyRepository extends JpaRepository<Company, Long> {}
