package com.company.organization.payload.outcome;

import com.company.organization.payload.BaseDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OutcomeProductDTO implements BaseDTO {
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
