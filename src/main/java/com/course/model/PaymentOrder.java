package com.course.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment_orders")
public class PaymentOrder {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private String recipient;
    @ManyToOne
    @JoinColumn(name = "budgetary_institution_id")
    private BudgetaryInstitution budgetaryInstitution;
    @ManyToOne
    @JoinColumn(name = "kekv_id")
    private Kekv kekv;
}
