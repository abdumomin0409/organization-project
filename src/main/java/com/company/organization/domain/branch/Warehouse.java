package com.company.organization.domain.branch;

import com.company.organization.domain.Auditable;
import com.company.organization.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Warehouse extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Double longitude;

    private Double latitude;

    @OneToOne(fetch = FetchType.LAZY)
    private User keeperId;

    @OneToOne(fetch = FetchType.LAZY)
    private Branch branch;

    private Boolean isActive = true;

    @Builder(builderMethodName = "warehouseBuilder")
    public Warehouse(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String name, String address,
                     Double longitude, Double latitude, User keeperId, Branch branch, Boolean isActive) {
        super(createdBy, updateBy, createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.keeperId = keeperId;
        this.branch = branch;
        this.isActive = isActive;
    }
}
