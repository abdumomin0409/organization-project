package com.company.organization.payload.branch;

import com.company.organization.payload.BaseDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchResponse implements BaseDTO {
    private Long id;
    private String name;
    private String address;
    private Long organizationId;
    private LocalDate workBegin;
}
