package com.course.mapper;

import com.course.dto.KekvDTO;
import com.course.model.Kekv;
import org.springframework.stereotype.Component;

@Component
public class KekvMapper {

    public KekvDTO toDto(Kekv entity) {
        if (entity == null) {
            return null;
        }

        return KekvDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public Kekv toEntity(KekvDTO dto) {
        if (dto == null) {
            return null;
        }

        Kekv entity = new Kekv();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}

