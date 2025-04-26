package com.course.service.treasuryDepartment;

import com.course.dto.TreasuryDepartmentDTO;
import com.course.mapper.TreasuryDepartmentMapper;
import com.course.model.TreasuryDepartment;
import com.course.repository.TreasuryDepartmentRepository;
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
public class TreasuryDepartmentServiceImpl implements TreasuryDepartmentService {

  private final TreasuryDepartmentRepository treasuryDepartmentRepository;
  private final TreasuryDepartmentMapper treasuryDepartmentMapper;

  @Override
  public TreasuryDepartmentDTO create(TreasuryDepartmentDTO dto) {
    TreasuryDepartment entity = treasuryDepartmentMapper.toEntity(dto);
    TreasuryDepartment saved = treasuryDepartmentRepository.save(entity);
    return treasuryDepartmentMapper.toDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public TreasuryDepartmentDTO getById(UUID id) {
    TreasuryDepartment entity = treasuryDepartmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Treasury Department not found"));
    return treasuryDepartmentMapper.toDto(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public List<TreasuryDepartmentDTO> getAll() {
    return treasuryDepartmentRepository.findAll().stream()
            .map(treasuryDepartmentMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public TreasuryDepartmentDTO update(UUID id, TreasuryDepartmentDTO dto) {
    TreasuryDepartment entity = treasuryDepartmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Treasury Department not found"));

    entity.setInstitutionServiceType(dto.getInstitutionServiceType());
    entity.setName(dto.getName());
    entity.setLocation(dto.getLocation());

    TreasuryDepartment updated = treasuryDepartmentRepository.save(entity);
    return treasuryDepartmentMapper.toDto(updated);
  }

  @Override
  public void delete(UUID id) {
    if (!treasuryDepartmentRepository.existsById(id)) {
      throw new EntityNotFoundException("Treasury Department not found");
    }
    treasuryDepartmentRepository.deleteById(id);
  }
}
