package com.course.service.kekv;

import com.course.dto.KekvDTO;
import java.util.List;
import java.util.UUID;

public interface KekvService {
    KekvDTO create(KekvDTO dto);
    KekvDTO getById(UUID id);
    List<KekvDTO> getAll();
    KekvDTO update(UUID id, KekvDTO dto);
    void delete(UUID id);
}

