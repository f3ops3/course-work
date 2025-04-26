package com.course.service.paymentOrder;

import com.course.dto.PaymentOrderDTO;
import java.util.List;
import java.util.UUID;

public interface PaymentOrderService {
    PaymentOrderDTO create(PaymentOrderDTO dto);
    PaymentOrderDTO getById(UUID id);
    List<PaymentOrderDTO> getAll();
    PaymentOrderDTO update(UUID id, PaymentOrderDTO dto);
    void delete(UUID id);
}

