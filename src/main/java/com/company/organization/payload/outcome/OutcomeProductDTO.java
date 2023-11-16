package com.company.organization.payload.outcome;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OutcomeProductDTO {
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
