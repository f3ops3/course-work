package com.course.mapper;

import com.course.dto.TreasuryDepartmentDTO;
import com.course.model.TreasuryDepartment;
import org.springframework.stereotype.Component;

@Component
public class TreasuryDepartmentMapper {

    public TreasuryDepartmentDTO toDto(TreasuryDepartment entity) {
        if (entity == null) {
            return null;
        }

        return TreasuryDepartmentDTO.builder()
                .id(entity.getId())
                .institutionServiceType(entity.getInstitutionServiceType())
                .name(entity.getName())
                .location(entity.getLocation())
                .build();
    }

    public TreasuryDepartment toEntity(TreasuryDepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        TreasuryDepartment entity = new TreasuryDepartment();
        entity.setId(dto.getId());
        entity.setInstitutionServiceType(dto.getInstitutionServiceType());
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());

        return entity;
    }
}

