package com.labsyncplus.labsync_investigations_microservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class InvestigationRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "investigationId", nullable = false)
    private Investigation investigation;
    private LocalDate investigationDate;
    private Double investigationCost;
    @Column(nullable = false)
    private boolean isDataAdded = false;
    @Column(nullable = false)
    private boolean isPrinted = false;

    public InvestigationRegister(Patient patient, Investigation investigation, LocalDate investigation_date, Double investigation_cost) {
        this.patient = patient;
        this.investigation = investigation;
        this.investigationDate = investigation_date;
        this.investigationCost = investigation_cost;
        this.isDataAdded = false;
        this.isPrinted = false;
    }
}
