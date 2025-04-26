package com.course.repository;

import com.course.model.TreasuryDepartment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreasuryDepartmentRepository extends JpaRepository<TreasuryDepartment, UUID> {
}
