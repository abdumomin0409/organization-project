package com.company.organization.payload.organization;

import com.company.organization.payload.BaseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationProductResponse implements BaseDTO {
    private Long id;
    private Long organizationId;
    private Long productId;
    private LocalDateTime createdAt;
}
