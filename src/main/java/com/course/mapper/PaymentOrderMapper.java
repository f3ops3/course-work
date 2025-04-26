package com.course.mapper;

import com.course.dto.PaymentOrderDTO;
import com.course.model.PaymentOrder;
import com.course.repository.BudgetaryInstitutionRepository;
import com.course.repository.KekvRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentOrderMapper {
  private final BudgetaryInstitutionRepository budgetaryInstitutionRepository;
  private final KekvRepository kekvRepository;

  public PaymentOrderDTO toDto(PaymentOrder entity) {
    if (entity == null) {
      return null;
    }

    return PaymentOrderDTO.builder()
            .id(entity.getId())
            .date(entity.getDate())
            .amount(entity.getAmount())
            .recipient(entity.getRecipient())
            .budgetaryInstitutionId(entity.getBudgetaryInstitution() != null ? entity.getBudgetaryInstitution().getId() : null)
            .kekvId(entity.getKekv() != null ? entity.getKekv().getId() : null)
            .build();
  }

  public PaymentOrder toEntity(PaymentOrderDTO dto) {
    if (dto == null) {
      return null;
    }

    PaymentOrder entity = new PaymentOrder();
    entity.setId(dto.getId());
    entity.setDate(dto.getDate());
    entity.setAmount(dto.getAmount());
    entity.setRecipient(dto.getRecipient());

    if (dto.getBudgetaryInstitutionId() != null) {
      entity.setBudgetaryInstitution(budgetaryInstitutionRepository.findById(dto.getBudgetaryInstitutionId())
              .orElseThrow(() -> new EntityNotFoundException("Budgetary Institution not found")));
    }

    if (dto.getKekvId() != null) {
      entity.setKekv(kekvRepository.findById(dto.getKekvId())
              .orElseThrow(() -> new EntityNotFoundException("KEKV not found")));
    }

    return entity;
  }
}
