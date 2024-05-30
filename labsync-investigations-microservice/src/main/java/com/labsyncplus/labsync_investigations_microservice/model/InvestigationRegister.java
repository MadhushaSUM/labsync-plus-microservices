package com.labsyncplus.labsync_investigations_microservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
public class InvestigationRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "investigation_id", nullable = false)
    private Investigation investigation;
    private LocalDate investigation_date;
    private Double investigation_cost;

    public InvestigationRegister(Patient patient, Investigation investigation, LocalDate investigation_date, Double investigation_cost) {
        this.patient = patient;
        this.investigation = investigation;
        this.investigation_date = investigation_date;
        this.investigation_cost = investigation_cost;
    }
}
