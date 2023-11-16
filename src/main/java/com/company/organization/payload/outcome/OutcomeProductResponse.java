package com.company.organization.payload.outcome;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OutcomeProductResponse {
    private Long id;
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
