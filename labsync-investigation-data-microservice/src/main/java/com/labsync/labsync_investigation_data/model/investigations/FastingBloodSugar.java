package com.labsync.labsync_investigation_data.model.investigations;

import com.labsync.labsync_investigation_data.model.InvestigationRegister;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FastingBloodSugar {
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "investigationRegisterId", nullable = false)
    private InvestigationRegister investigationRegister;
    private double fbsValue;

    public FastingBloodSugar(InvestigationRegister investigationRegister, double fbsValue) {
        this.investigationRegister = investigationRegister;
        this.fbsValue = fbsValue;
    }
}
