package com.company.organization.payload.branch;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BranchResponse {
    private String name;
    private String address;
    private Long organizationId;
    private LocalDate workBegin;
}
