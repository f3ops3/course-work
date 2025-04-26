package com.course.controller;

import com.course.dto.KekvDTO;
import com.course.service.kekv.KekvService;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kekvs")
public class KekvController {
  private final KekvService service;

  public KekvController(KekvService service) {
    this.service = service;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("kekvs", service.getAll());
    return "kekvs/list";
  }

  @GetMapping("/create")
  public String createForm(Model model) {
    model.addAttribute("kekv", new KekvDTO());
    return "kekvs/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute KekvDTO dto) {
    service.create(dto);
    return "redirect:/kekvs";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    model.addAttribute("kekv", service.getById(id));
    return "kekvs/edit";
  }

  @PostMapping("/edit/{id}")
  public String edit(@PathVariable UUID id, @ModelAttribute KekvDTO dto) {
    service.update(id, dto);
    return "redirect:/kekvs";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable UUID id) {
    service.delete(id);
    return "redirect:/kekvs";
  }
}
