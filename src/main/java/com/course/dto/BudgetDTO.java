package com.course.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDTO {
    private UUID id;
    private Integer year;
    private BigDecimal totalLimit;
    private String purpose;
    private UUID kekvId;
    private UUID budgetaryInstitutionId;
}

