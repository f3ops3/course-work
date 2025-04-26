package com.course.repository;

import com.course.model.BudgetaryInstitution;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetaryInstitutionRepository extends JpaRepository<BudgetaryInstitution, UUID> {
}
