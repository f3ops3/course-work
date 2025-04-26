package com.course.controller;

import com.course.dto.BudgetaryInstitutionDTO;
import com.course.service.budgetaryInstitution.BudgetaryInstitutionService;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/budgetary-institutions")
public class BudgetaryInstitutionController {
  private final BudgetaryInstitutionService service;

  public BudgetaryInstitutionController(BudgetaryInstitutionService service) {
    this.service = service;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("budgetaryInstitutions", service.getAll());
    return "budgetary-institutions/list";
  }

  @GetMapping("/create")
  public String createForm(Model model) {
    model.addAttribute("budgetaryInstitution", new BudgetaryInstitutionDTO());
    return "budgetary-institutions/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute BudgetaryInstitutionDTO dto) {
    service.create(dto);
    return "redirect:/budgetary-institutions";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    model.addAttribute("budgetaryInstitution", service.getById(id));
    return "budgetary-institutions/edit";
  }

  @PostMapping("/edit/{id}")
  public String edit(@PathVariable UUID id, @ModelAttribute BudgetaryInstitutionDTO dto) {
    service.update(id, dto);
    return "redirect:/budgetary-institutions";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable UUID id) {
    service.delete(id);
    return "redirect:/budgetary-institutions";
  }
}
