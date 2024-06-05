package com.labsync.labsync_investigation_data.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "investigation_register_investigations",
            joinColumns = @JoinColumn(name = "investigation_register_id"),
            inverseJoinColumns = @JoinColumn(name = "investigation_id")
    )
    private List<Investigation> investigations;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "investigation_cost")
    private double cost;

    @Column(name = "is_printed", nullable = false)
    private boolean isPrinted = false;

    @Column(name = "is_data_added", nullable = false)
    private boolean isDataAdded = false;
}
