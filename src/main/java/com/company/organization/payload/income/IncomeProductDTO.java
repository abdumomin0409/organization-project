package com.company.organization.payload.income;

import com.company.organization.payload.BaseDTO;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeProductDTO implements BaseDTO {
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
