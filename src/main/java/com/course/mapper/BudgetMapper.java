package com.course.mapper;

import com.course.dto.BudgetDTO;
import com.course.model.Budget;
import com.course.repository.BudgetaryInstitutionRepository;
import com.course.repository.KekvRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BudgetMapper {

    private final KekvRepository kekvRepository;
    private final BudgetaryInstitutionRepository budgetaryInstitutionRepository;

    public BudgetDTO toDto(Budget entity) {
        if (entity == null) {
            return null;
        }

        return BudgetDTO.builder()
                .id(entity.getId())
                .year(entity.getYear())
                .totalLimit(entity.getTotalLimit())
                .purpose(entity.getPurpose())
                .kekvId(entity.getKekv() != null ? entity.getKekv().getId() : null)
                .budgetaryInstitutionId(entity.getBudgetaryInstitution() != null ?
                        entity.getBudgetaryInstitution().getId() : null)
                .build();
    }

    public Budget toEntity(BudgetDTO dto) {
        if (dto == null) {
            return null;
        }

        Budget entity = new Budget();
        entity.setId(dto.getId());
        entity.setYear(dto.getYear());
        entity.setTotalLimit(dto.getTotalLimit());
        entity.setPurpose(dto.getPurpose());

        if (dto.getKekvId() != null) {
            entity.setKekv(kekvRepository.findById(dto.getKekvId())
                    .orElseThrow(() -> new EntityNotFoundException("KEKV not found")));
        }

        if (dto.getBudgetaryInstitutionId() != null) {
            entity.setBudgetaryInstitution(budgetaryInstitutionRepository.findById(dto.getBudgetaryInstitutionId())
                    .orElseThrow(() -> new EntityNotFoundException("Budgetary Institution not found")));
        }

        return entity;
    }
}
