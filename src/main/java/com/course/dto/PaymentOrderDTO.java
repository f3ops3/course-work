package com.course.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderDTO {
    private UUID id;
    private LocalDate date;
    private BigDecimal amount;
    private String recipient;
    private UUID budgetaryInstitutionId;
    private UUID kekvId;
}

