package com.labsyncplus.labsync_investigations_microservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InvestigationFieldRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "investigation_id")
    private Investigation investigation;

    private String fieldName;
    private double high;
    private double low;
}
