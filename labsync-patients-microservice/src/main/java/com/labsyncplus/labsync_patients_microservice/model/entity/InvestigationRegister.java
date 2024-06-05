package com.labsyncplus.labsync_patients_microservice.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "investigation_register")
@Data
public class InvestigationRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "investigation_id")
    private Investigation investigation;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "investigation_cost")
    private double cost;

    @Column(name = "is_printed", nullable = false)
    private boolean isPrinted = false;

    @Column(name = "is_data_added", nullable = false)
    private boolean isDataAdded = false;
}
