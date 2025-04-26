package com.course.controller;

import com.course.dto.TreasuryDepartmentDTO;
import com.course.service.treasuryDepartment.TreasuryDepartmentService;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/treasury-departments")
public class TreasuryDepartmentController {
  private final TreasuryDepartmentService service;

  public TreasuryDepartmentController(TreasuryDepartmentService service) {
    this.service = service;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("treasuryDepartments", service.getAll());
    return "treasury-departments/list";
  }

  @GetMapping("/create")
  public String createForm(Model model) {
    model.addAttribute("treasuryDepartment", new TreasuryDepartmentDTO());
    return "treasury-departments/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute TreasuryDepartmentDTO dto) {
    service.create(dto);
    return "redirect:/treasury-departments";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    model.addAttribute("treasuryDepartment", service.getById(id));
    return "treasury-departments/edit";
  }

  @PostMapping("/edit/{id}")
  public String edit(@PathVariable UUID id, @ModelAttribute TreasuryDepartmentDTO dto) {
    service.update(id, dto);
    return "redirect:/treasury-departments";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable UUID id) {
    service.delete(id);
    return "redirect:/treasury-departments";
  }
}
