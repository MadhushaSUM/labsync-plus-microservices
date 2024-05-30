package com.labsync.labsync_investigation_data.Dao;

import com.labsync.labsync_investigation_data.model.investigations.FastingBloodSugar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FastingBloodSugarDao extends JpaRepository<FastingBloodSugar, Integer> {
    Optional<FastingBloodSugar> findByInvestigationRegisterId(int investigationRegisterId);
}
