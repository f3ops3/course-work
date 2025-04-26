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
public class BudgetaryInstitutionDTO {
    private UUID id;
    private String edrpouCode;
    private String location;
    private String name;
    private UUID treasuryDepartmentId;
}
