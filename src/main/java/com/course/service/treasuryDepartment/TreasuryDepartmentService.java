package com.course.service.treasuryDepartment;

import com.course.dto.TreasuryDepartmentDTO;
import java.util.List;
import java.util.UUID;

public interface TreasuryDepartmentService {
    TreasuryDepartmentDTO create(TreasuryDepartmentDTO dto);
    TreasuryDepartmentDTO getById(UUID id);
    List<TreasuryDepartmentDTO> getAll();
    TreasuryDepartmentDTO update(UUID id, TreasuryDepartmentDTO dto);
    void delete(UUID id);
}
