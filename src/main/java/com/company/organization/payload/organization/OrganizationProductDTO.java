package com.company.organization.payload.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrganizationProductDTO {
    Long organizationId;
    ArrayList<Long> productIdList;
}
