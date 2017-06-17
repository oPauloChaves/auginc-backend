package com.opaulochaves.auginc.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource( collectionResourceRel = "users", path = "users" )
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail( String email );

}