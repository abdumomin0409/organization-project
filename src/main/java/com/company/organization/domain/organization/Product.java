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
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Boolean isActive = true;

    @Builder(builderMethodName = "productBuilder")
    public Product(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String name, String description, Boolean isActive) {
        super(createdBy, updateBy, createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }
}
