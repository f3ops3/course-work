package com.course.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private Integer year;
    private BigDecimal totalLimit;
    private String purpose;
    @ManyToOne
    @JoinColumn(name = "kekv_id")
    private Kekv kekv;
    @ManyToOne
    @JoinColumn(name = "budgetary_institution_id")
    private BudgetaryInstitution budgetaryInstitution;
}
