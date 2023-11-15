package com.company.organization.domain.income;

import com.company.organization.domain.Auditable;
import com.company.organization.domain.branch.Warehouse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Income extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Warehouse warehouse;

     private Boolean isActive = true;

    @Builder(builderMethodName = "incomeBuilder")
    public Income(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, Warehouse warehouse, Boolean isActive) {
        super(createdBy, updateBy, createdAt, updatedAt);
        this.id = id;
        this.warehouse = warehouse;
        this.isActive = isActive;
    }
}
