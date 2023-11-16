package com.company.organization.payload.organization;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationProductResponse {
    private Long id;
    private Long organizationId;
    private Long productId;
    private LocalDateTime createdAt;
}
