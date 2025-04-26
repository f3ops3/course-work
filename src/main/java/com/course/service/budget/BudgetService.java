package com.course.service.budget;

import com.course.dto.BudgetDTO;
import java.util.List;
import java.util.UUID;

public interface BudgetService {
    BudgetDTO create(BudgetDTO dto);
    BudgetDTO getById(UUID id);
    List<BudgetDTO> getAll();
    BudgetDTO update(UUID id, BudgetDTO dto);
    void delete(UUID id);
}

