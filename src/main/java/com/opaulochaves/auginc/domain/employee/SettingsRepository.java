package com.opaulochaves.auginc.domain.employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {

    List<Settings> findByEmployee_Email(String email);

    Settings findByTypeAndEmployee_Email(Settings.SettingsType type, String email);

}