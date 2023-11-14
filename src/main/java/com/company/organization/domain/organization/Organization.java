package com.company.organization.domain.organization;

import com.company.organization.domain.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Organization extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

     private Boolean isActive = true;

    @Builder(builderMethodName = "organizationBuilder")
    public Organization(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String name, String email) {
        super(createdBy, updateBy, createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
