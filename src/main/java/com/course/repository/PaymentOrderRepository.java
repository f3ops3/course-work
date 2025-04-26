package com.course.repository;

import com.course.model.PaymentOrder;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, UUID> {
}
