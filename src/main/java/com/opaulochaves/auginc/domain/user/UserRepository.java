package com.opaulochaves.auginc.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail( @Param("email") String email );

}
