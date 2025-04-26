package com.course.service.budgetaryInstitution;

import com.course.dto.BudgetaryInstitutionDTO;
import com.course.mapper.BudgetaryInstitutionMapper;
import com.course.model.BudgetaryInstitution;
import com.course.model.TreasuryDepartment;
import com.course.repository.BudgetaryInstitutionRepository;
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
public class BudgetaryInstitutionServiceImpl implements BudgetaryInstitutionService {

    private final BudgetaryInstitutionRepository budgetaryInstitutionRepository;
    private final BudgetaryInstitutionMapper budgetaryInstitutionMapper;
    private final TreasuryDepartmentRepository treasuryDepartmentRepository;

    @Override
    public BudgetaryInstitutionDTO create(BudgetaryInstitutionDTO dto) {
        BudgetaryInstitution entity = budgetaryInstitutionMapper.toEntity(dto);

        if (dto.getTreasuryDepartmentId() != null) {
            TreasuryDepartment department = treasuryDepartmentRepository.findById(dto.getTreasuryDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("TreasuryDepartment not found"));
            entity.setTreasuryDepartment(department);
        }

        BudgetaryInstitution saved = budgetaryInstitutionRepository.save(entity);
        return budgetaryInstitutionMapper.toDto(saved);
    }

    @Override
    public BudgetaryInstitutionDTO update(UUID id, BudgetaryInstitutionDTO dto) {
        BudgetaryInstitution existing = budgetaryInstitutionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BudgetaryInstitution not found"));

        existing.setName(dto.getName());
        existing.setLocation(dto.getLocation());
        existing.setEdrpouCode(dto.getEdrpouCode());

        if (dto.getTreasuryDepartmentId() != null) {
            TreasuryDepartment department = treasuryDepartmentRepository.findById(dto.getTreasuryDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("TreasuryDepartment not found"));
            existing.setTreasuryDepartment(department);
        } else {
            existing.setTreasuryDepartment(null);
        }

        BudgetaryInstitution updated = budgetaryInstitutionRepository.save(existing);
        return budgetaryInstitutionMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public BudgetaryInstitutionDTO getById(UUID id) {
        BudgetaryInstitution entity = budgetaryInstitutionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BudgetaryInstitution not found"));
        return budgetaryInstitutionMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BudgetaryInstitutionDTO> getAll() {
        return budgetaryInstitutionRepository.findAll()
                .stream()
                .map(budgetaryInstitutionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        if (!budgetaryInstitutionRepository.existsById(id)) {
            throw new EntityNotFoundException("BudgetaryInstitution not found");
        }
        budgetaryInstitutionRepository.deleteById(id);
    }
}
