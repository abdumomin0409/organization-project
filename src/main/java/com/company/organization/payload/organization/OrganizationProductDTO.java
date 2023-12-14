package com.company.organization.payload.organization;

import com.company.organization.payload.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrganizationProductDTO implements BaseDTO {
    Long organizationId;
    ArrayList<Long> productIdList;
}
