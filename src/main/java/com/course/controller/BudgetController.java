package com.course.controller;

import com.course.dto.BudgetDTO;
import com.course.service.budget.BudgetService;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/budgets")
public class BudgetController {
  private final BudgetService service;

  public BudgetController(BudgetService service) {
    this.service = service;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("budgets", service.getAll());
    return "budgets/list";
  }

  @GetMapping("/create")
  public String createForm(Model model) {
    model.addAttribute("budget", new BudgetDTO());
    return "budgets/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute BudgetDTO dto) {
    service.create(dto);
    return "redirect:/budgets";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    model.addAttribute("budget", service.getById(id));
    return "budgets/edit";
  }

  @PostMapping("/edit/{id}")
  public String edit(@PathVariable UUID id, @ModelAttribute BudgetDTO dto) {
    service.update(id, dto);
    return "redirect:/budgets";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable UUID id) {
    service.delete(id);
    return "redirect:/budgets";
  }
}
