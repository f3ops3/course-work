package com.course;

import com.course.model.Budget;
import com.course.model.BudgetaryInstitution;
import com.course.model.Kekv;
import com.course.model.PaymentOrder;
import com.course.model.TreasuryDepartment;
import com.course.repository.BudgetRepository;
import com.course.repository.BudgetaryInstitutionRepository;
import com.course.repository.KekvRepository;
import com.course.repository.PaymentOrderRepository;
import com.course.repository.TreasuryDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {
  private final TreasuryDepartmentRepository treasuryDepartmentRepository;
  private final BudgetaryInstitutionRepository budgetaryInstitutionRepository;
  private final KekvRepository kekvRepository;
  private final BudgetRepository budgetRepository;
  private final PaymentOrderRepository paymentOrderRepository;

  @Override
  public void run(String... args) {
    if (treasuryDepartmentRepository.count() == 0) {
      for (int i = 1; i <= 5; i++) {
        TreasuryDepartment department = new TreasuryDepartment();
        department.setName("Казначейство №" + i);
        department.setInstitutionServiceType("Тип обслуговування " + i);
        department.setLocation("Місто " + i);
        treasuryDepartmentRepository.save(department);
      }
    }

    if (budgetaryInstitutionRepository.count() == 0) {
      List<TreasuryDepartment> departments = treasuryDepartmentRepository.findAll();
      for (int i = 1; i <= 5; i++) {
        BudgetaryInstitution institution = new BudgetaryInstitution();
        institution.setName("Установа №" + i);
        institution.setEdrpouCode("1234567" + i);
        institution.setLocation("Локація " + i);
        institution.setTreasuryDepartment(departments.get(i % departments.size()));
        budgetaryInstitutionRepository.save(institution);
      }
    }

    if (kekvRepository.count() == 0) {
      for (int i = 1; i <= 5; i++) {
        Kekv kekv = new Kekv();
        kekv.setName("КЕКВ" + (1000 + i));
        kekv.setDescription("Опис для КЕКВ " + i);
        kekvRepository.save(kekv);
      }
    }

    if (budgetRepository.count() == 0) {
      List<BudgetaryInstitution> institutions = budgetaryInstitutionRepository.findAll();
      List<Kekv> kekvs = kekvRepository.findAll();
      for (int i = 1; i <= 5; i++) {
        Budget budget = new Budget();
        budget.setYear(2025);
        budget.setTotalLimit(BigDecimal.valueOf(100000 + i * 5000));
        budget.setPurpose("Ціль бюджету №" + i);
        budget.setBudgetaryInstitution(institutions.get(i % institutions.size()));
        budget.setKekv(kekvs.get(i % kekvs.size()));
        budgetRepository.save(budget);
      }
    }

    if (paymentOrderRepository.count() == 0) {
      List<BudgetaryInstitution> institutions = budgetaryInstitutionRepository.findAll();
      List<Kekv> kekvs = kekvRepository.findAll();
      for (int i = 1; i <= 5; i++) {
        PaymentOrder order = new PaymentOrder();
        order.setDate(LocalDate.now().minusDays(i));
        order.setAmount(BigDecimal.valueOf(2000 + i * 1000));
        order.setRecipient("Отримувач №" + i);
        order.setBudgetaryInstitution(institutions.get(i % institutions.size()));
        order.setKekv(kekvs.get(i % kekvs.size()));
        paymentOrderRepository.save(order);
      }
    }
  }
}
