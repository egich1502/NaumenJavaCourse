package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.services.ProducerService;

@Controller
@RequestMapping("/producer")
public class ProducerController {

  private final ProducerService producerService;

  @Autowired
  public ProducerController(ProducerService producerService) {
    this.producerService = producerService;
  }

  @GetMapping("/save")
  public String saveProducer() {
    return "producer/save";
  }

  @PostMapping("/save")
  public String saveProducer(String name, Model model){
    producerService.saveProducer(name);
    return "redirect:/producer/getAll";
  }

  @GetMapping("/getAll")
  public String getAllProducers(Model model) {
    model.addAttribute("allProducers", producerService.getAllProducers());
    return "producer/getAll";
  }

  @GetMapping("/{id}")
  public String getProducer(@PathVariable("id") int id, Model model){
    model.addAttribute("producer", producerService.getProducer(id));
    return "producer/get";
  }
  @DeleteMapping("/delete/{id}")
  public String deleteProducer(@PathVariable("id") int id){
    producerService.deleteProducer(id);
    return "redirect:/producer/getAll";
  }

  @GetMapping("/edit/{id}")
  public String editProducer(@PathVariable("id")int id, Model model){
    model.addAttribute("producer", producerService.getProducer(id));
    return "producer/edit";
  }

 @PatchMapping("/edit/{id}")
  public String updateProducer(@ModelAttribute("producer")Producer producer, @PathVariable("id") int id){
    producerService.updateProducer(id, producer);
    return "redirect:/producer/{id}";
  }
}
