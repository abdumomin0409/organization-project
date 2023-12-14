package com.company.organization.payload.branch;

import com.company.organization.payload.BaseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseResponse implements BaseDTO {
    private Long id;
    private String name;
    private String address;
    private Long keeperId;
    private Long branchId;
    private Double longitude;
    private Double latitude;
}
