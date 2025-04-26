package com.course.service.budget;

import com.course.dto.BudgetDTO;
import com.course.mapper.BudgetMapper;
import com.course.model.Budget;
import com.course.repository.BudgetRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;

    @Override
    public BudgetDTO create(BudgetDTO dto) {
        Budget entity = budgetMapper.toEntity(dto);
        Budget saved = budgetRepository.save(entity);
        return budgetMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public BudgetDTO getById(UUID id) {
        Budget entity = budgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget not found"));
        return budgetMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BudgetDTO> getAll() {
        return budgetRepository.findAll().stream()
                .map(budgetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BudgetDTO update(UUID id, BudgetDTO dto) {
        Budget entity = budgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget not found"));

        entity.setYear(dto.getYear());
        entity.setTotalLimit(dto.getTotalLimit());
        entity.setPurpose(dto.getPurpose());

        if (dto.getKekvId() != null) {
            entity.setKekv(budgetMapper.toEntity(dto).getKekv());
        }

        if (dto.getBudgetaryInstitutionId() != null) {
            entity.setBudgetaryInstitution(budgetMapper.toEntity(dto).getBudgetaryInstitution());
        }

        Budget updated = budgetRepository.save(entity);
        return budgetMapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!budgetRepository.existsById(id)) {
            throw new EntityNotFoundException("Budget not found");
        }
        budgetRepository.deleteById(id);
    }
}
