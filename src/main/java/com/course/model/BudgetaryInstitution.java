package com.course.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "budgetary_institutions")
public class BudgetaryInstitution {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(nullable = false, unique = true)
    private String edrpouCode;
    private String location;
    private String name;
    @ManyToOne
    @JoinColumn(name = "treasury_department_id")
    private TreasuryDepartment treasuryDepartment;
    @OneToMany(mappedBy = "budgetaryInstitution")
    private List<Budget> budgets;
    @OneToMany(mappedBy = "budgetaryInstitution")
    private List<PaymentOrder> paymentOrders = new ArrayList<>();
}
