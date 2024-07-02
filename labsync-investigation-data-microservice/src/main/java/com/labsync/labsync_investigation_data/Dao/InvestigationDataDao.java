package com.labsync.labsync_investigation_data.Dao;

import com.labsync.labsync_investigation_data.model.entity.InvestigationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvestigationDataDao extends JpaRepository<InvestigationData, Long> {
    List<InvestigationData> findByInvestigationRegisterIdAndInvestigationId(long investigationRegisterId, long investigationId);

    @Query("SELECT id FROM InvestigationData id WHERE id.investigationRegister.patient.id = :patientId " +
            "AND id.investigation.id = :investigationId " +
            "AND id.investigationRegister.registeredDate BETWEEN :startDate AND :endDate")
    List<InvestigationData> findByPatientIdAndInvestigationIdAndDateRange(
            @Param("patientId") Long patientId,
            @Param("investigationId") Long investigationId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT register FROM InvestigationRegister register WHERE register.registeredDate BETWEEN :startDate AND :endDate")
    List<InvestigationData> findInvestigationDataBetweenDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
