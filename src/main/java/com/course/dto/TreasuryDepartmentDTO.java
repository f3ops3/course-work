package com.course.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreasuryDepartmentDTO {
    private UUID id;
    private String institutionServiceType;
    private String name;
    private String location;
}

