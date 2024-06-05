package com.labsyncplus.labsync_investigations_microservice.model.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "investigation_data")
@Data
public class InvestigationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "investigation_register_id")
    private InvestigationRegister investigationRegister;

    @ManyToOne
    @JoinColumn(name = "investigation_id")
    private Investigation investigation;

    @JdbcTypeCode(SqlTypes.JSON)
    private String data;

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void setDataFromMap(Map<String, Object> map) throws JsonProcessingException {
        this.data = objectMapper.writeValueAsString(map);
    }

    public Map<String, Object> getDataAsMap() throws JsonProcessingException {
        return objectMapper.readValue(this.data, Map.class);
    }
}
