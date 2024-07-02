package com.labsyncplus.labsync_investigations_microservice.dao;

import com.labsyncplus.labsync_investigations_microservice.model.entity.InvestigationRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface InvestigationRegisterDao extends JpaRepository<InvestigationRegister, Long> {

    List<InvestigationRegister> findByIsDataAddedFalse();

    List<InvestigationRegister> findByIsPrintedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE InvestigationRegister ir SET ir.isDataAdded = true WHERE ir.id = :id")
    void setDataAdded(@Param("id") Long id);

    @Query("SELECT register FROM InvestigationRegister register WHERE register.registeredDate BETWEEN :startDate AND :endDate")
    List<InvestigationRegister> findInvestigationRegisterBetweenDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
