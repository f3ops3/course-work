package com.course.service.paymentOrder;

import com.course.dto.PaymentOrderDTO;
import com.course.mapper.PaymentOrderMapper;
import com.course.model.PaymentOrder;
import com.course.repository.PaymentOrderRepository;
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
public class PaymentOrderServiceImpl implements PaymentOrderService {

  private final PaymentOrderRepository paymentOrderRepository;
  private final PaymentOrderMapper paymentOrderMapper;

  @Override
  public PaymentOrderDTO create(PaymentOrderDTO dto) {
    PaymentOrder entity = paymentOrderMapper.toEntity(dto);
    PaymentOrder saved = paymentOrderRepository.save(entity);
    return paymentOrderMapper.toDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public PaymentOrderDTO getById(UUID id) {
    PaymentOrder entity = paymentOrderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Payment Order not found"));
    return paymentOrderMapper.toDto(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public List<PaymentOrderDTO> getAll() {
    return paymentOrderRepository.findAll().stream()
            .map(paymentOrderMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public PaymentOrderDTO update(UUID id, PaymentOrderDTO dto) {
    PaymentOrder entity = paymentOrderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Payment Order not found"));

    entity.setDate(dto.getDate());
    entity.setAmount(dto.getAmount());
    entity.setRecipient(dto.getRecipient());

    if (dto.getBudgetaryInstitutionId() != null) {
      entity.setBudgetaryInstitution(paymentOrderMapper.toEntity(dto).getBudgetaryInstitution());
    }

    if (dto.getKekvId() != null) {
      entity.setKekv(paymentOrderMapper.toEntity(dto).getKekv());
    }

    PaymentOrder updated = paymentOrderRepository.save(entity);
    return paymentOrderMapper.toDto(updated);
  }

  @Override
  public void delete(UUID id) {
    if (!paymentOrderRepository.existsById(id)) {
      throw new EntityNotFoundException("Payment Order not found");
    }
    paymentOrderRepository.deleteById(id);
  }
}
