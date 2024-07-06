package com.labsyncplus.labsync_investigations_microservice.model.entity;

import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "normal_range_rule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalRangeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "investigation_id", nullable = false)
    private long investigationId;
    private String fieldName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "data", nullable = false)
    private List<NormalRange> normalRanges;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NormalRange {
        private String id;
        private List<String> genders;
        private Integer ageLowerBound;
        private Integer ageUpperBound;
        private Double valueLowerBound;
        private Double valueUpperBound;
    }
}
