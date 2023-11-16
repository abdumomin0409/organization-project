package com.company.organization.payload.branch;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchResponse {
    private Long id;
    private String name;
    private String address;
    private Long organizationId;
    private LocalDate workBegin;
}
