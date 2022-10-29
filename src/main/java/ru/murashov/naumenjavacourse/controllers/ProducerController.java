package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.services.ProducerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/producer")
public class ProducerController {

  private final ProducerService producerService;

  @Autowired
  public ProducerController(ProducerService producerService) {
    this.producerService = producerService;
  }

  @GetMapping("/save")
  public String saveProducer(@ModelAttribute("producer") Producer producer) {
    return "producer/save";
  }

  @PostMapping("/save")
  public String saveProducer(@ModelAttribute("producer") @Valid Producer producer, BindingResult bindingResult) {
    if (bindingResult.hasErrors())
      return "producer/save";
    producerService.saveProducer(producer);
    return "redirect:/producer/getAll";
  }

  @GetMapping("/getAll")
  public String getAllProducers(Model model) {
    model.addAttribute("allProducers", producerService.getAllProducers());
    return "producer/getAll";
  }

  @GetMapping("/{id}")
  public String getProducer(@PathVariable("id") int id, Model model) {
    model.addAttribute("producer", producerService.getProducer(id));
    return "producer/get";
  }

  @DeleteMapping("/delete/{id}")
  public String deleteProducer(@PathVariable("id") int id) {
    producerService.deleteProducer(id);
    return "redirect:/producer/getAll";
  }

  @GetMapping("/edit/{id}")
  public String editProducer(@PathVariable("id") int id, Model model) {
    model.addAttribute("producer", producerService.getProducer(id));
    return "producer/edit";
  }

  @PatchMapping("/edit/{id}")
  public String updateProducer(@ModelAttribute("producer") @Valid Producer producer, BindingResult bindingResult,
      @PathVariable("id") int id) {
    if (bindingResult.hasErrors())
      return "producer/edit";
    producerService.updateProducer(id, producer);
    return "redirect:/producer/{id}";
  }
}
