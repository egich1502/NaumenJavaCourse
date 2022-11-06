package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.services.ProducerService;

@Controller
@RequestMapping("producer")
public class ProducerController {

  private final ProducerService producerService;

  @Autowired
  public ProducerController(ProducerService producerService) {
    this.producerService = producerService;
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("save")
  public String saveProducer() {
    return "producer/save";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("save")
  public String saveProducer(String name) {
    producerService.saveProducer(name);
    return "redirect:/producer/getAll";
  }

  @GetMapping("getAll")
  public String getAllProducers(Model model) {
    model.addAttribute("allProducers", producerService.getAllProducers());
    return "producer/getAll";
  }

  @GetMapping("{id}")
  public String getProducer(@PathVariable("id") int id, Model model) {
    model.addAttribute("producer", producerService.getProducer(id));
    return "producer/get";
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping("{id}/delete")
  public String deleteProducer(@PathVariable("id") int id) {
    producerService.deleteProducer(id);
    return "redirect:/producer/getAll";
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("{id}/edit")
  public String editProducer(@PathVariable("id") int id, Model model) {
    model.addAttribute("producer", producerService.getProducer(id));
    return "producer/edit";
  }

  @Secured("ROLE_ADMIN")
  @PatchMapping("{id}/edit")
  public String updateProducer(@ModelAttribute("producer") Producer producer,
      @PathVariable("id") int id) {
    producerService.updateProducer(id, producer);
    return "redirect:/producer/{id}";
  }
}
