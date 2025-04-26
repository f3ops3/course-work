package com.course.service.kekv;

import com.course.dto.KekvDTO;
import com.course.mapper.KekvMapper;
import com.course.model.Kekv;
import com.course.repository.KekvRepository;
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
public class KekvServiceImpl implements KekvService {

    private final KekvRepository kekvRepository;
    private final KekvMapper kekvMapper;

    @Override
    public KekvDTO create(KekvDTO dto) {
        Kekv entity = kekvMapper.toEntity(dto);
        kekvRepository.save(entity);
        return kekvMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public KekvDTO getById(UUID id) {
        Kekv entity = kekvRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KEKV not found"));
        return kekvMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KekvDTO> getAll() {
        return kekvRepository.findAll().stream()
                .map(kekvMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KekvDTO update(UUID id, KekvDTO dto) {
        Kekv entity = kekvRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KEKV not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        Kekv updated = kekvRepository.save(entity);
        return kekvMapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!kekvRepository.existsById(id)) {
            throw new EntityNotFoundException("KEKV not found");
        }
        kekvRepository.deleteById(id);
    }
}
