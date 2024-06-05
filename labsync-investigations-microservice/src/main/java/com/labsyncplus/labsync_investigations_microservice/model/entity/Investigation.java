package com.labsyncplus.labsync_investigations_microservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "investigation")
@Data
public class Investigation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String specimen;
}
