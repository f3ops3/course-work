package com.course.controller;

import com.course.dto.PaymentOrderDTO;
import com.course.service.paymentOrder.PaymentOrderService;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment-orders")
public class PaymentOrderController {
  private final PaymentOrderService service;

  public PaymentOrderController(PaymentOrderService service) {
    this.service = service;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("paymentOrders", service.getAll());
    return "payment-orders/list";
  }

  @GetMapping("/create")
  public String createForm(Model model) {
    model.addAttribute("paymentOrder", new PaymentOrderDTO());
    return "payment-orders/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute PaymentOrderDTO dto) {
    service.create(dto);
    return "redirect:/payment-orders";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    model.addAttribute("paymentOrder", service.getById(id));
    return "payment-orders/edit";
  }

  @PostMapping("/edit/{id}")
  public String edit(@PathVariable UUID id, @ModelAttribute PaymentOrderDTO dto) {
    service.update(id, dto);
    return "redirect:/payment-orders";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable UUID id) {
    service.delete(id);
    return "redirect:/payment-orders";
  }
}
