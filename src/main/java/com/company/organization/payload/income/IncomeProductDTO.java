package com.company.organization.payload.income;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeProductDTO {
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
