package com.course.mapper;

import com.course.dto.BudgetaryInstitutionDTO;
import com.course.model.BudgetaryInstitution;
import org.springframework.stereotype.Component;

@Component
public class BudgetaryInstitutionMapper {

    public BudgetaryInstitutionDTO toDto(BudgetaryInstitution entity) {
        if (entity == null) {
            return null;
        }

        return BudgetaryInstitutionDTO.builder()
                .id(entity.getId())
                .edrpouCode(entity.getEdrpouCode())
                .location(entity.getLocation())
                .name(entity.getName())
                .treasuryDepartmentId(
                        entity.getTreasuryDepartment() != null ? entity.getTreasuryDepartment().getId() : null
                )
                .build();
    }

    public BudgetaryInstitution toEntity(BudgetaryInstitutionDTO dto) {
        if (dto == null) {
            return null;
        }

        BudgetaryInstitution entity = new BudgetaryInstitution();
        entity.setId(dto.getId());
        entity.setEdrpouCode(dto.getEdrpouCode());
        entity.setLocation(dto.getLocation());
        entity.setName(dto.getName());

        return entity;
    }
}
