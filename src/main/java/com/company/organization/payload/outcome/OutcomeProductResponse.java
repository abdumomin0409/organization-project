package com.company.organization.payload.outcome;

import com.company.organization.payload.BaseDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OutcomeProductResponse implements BaseDTO {
    private Long id;
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
