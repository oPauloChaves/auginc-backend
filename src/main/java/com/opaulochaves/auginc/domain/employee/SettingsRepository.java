package com.opaulochaves.auginc.domain.employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    List<Settings> findByEmployee_Email(String email);

    Settings findByTypeAndEmployee_Email(Settings.SettingsType type, String email);

}