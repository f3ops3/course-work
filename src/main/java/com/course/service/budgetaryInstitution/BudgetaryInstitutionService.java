package com.course.service.budgetaryInstitution;

import com.course.dto.BudgetaryInstitutionDTO;
import java.util.List;
import java.util.UUID;

public interface BudgetaryInstitutionService {
    BudgetaryInstitutionDTO create(BudgetaryInstitutionDTO dto);
    BudgetaryInstitutionDTO update(UUID id, BudgetaryInstitutionDTO dto);
    BudgetaryInstitutionDTO getById(UUID id);
    List<BudgetaryInstitutionDTO> getAll();
    void delete(UUID id);
}

