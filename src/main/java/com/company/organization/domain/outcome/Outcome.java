package com.company.organization.domain.outcome;

import com.company.organization.domain.Auditable;
import com.company.organization.domain.branch.Warehouse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Outcome extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    private Boolean isActive = true;

    @Builder(builderMethodName = "outcomeBuilder")
    public Outcome(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, Warehouse warehouse, Boolean isActive) {
        super(createdBy, updateBy, createdAt, updatedAt);
        this.id = id;
        this.warehouse = warehouse;
        this.isActive = isActive;
    }
}
