package com.company.organization.payload.branch;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseResponse {
    private Long id;
    private String name;
    private String address;
    private Long keeperId;
    private Long branchId;
    private Double longitude;
    private Double latitude;
}
