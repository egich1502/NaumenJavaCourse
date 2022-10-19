package ru.murashov.naumenjavacourse.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.services.ShopService;

@RestController
public class StoreController {

  private final ShopService shopService;

  @Autowired
  public StoreController(ShopService shopService) {
    this.shopService = shopService;
  }

  @GetMapping("/saveproducer/{name}")
  @ResponseBody
  public Producer save(@PathVariable String name) {
    return shopService.saveProducer(name);
  }

  @GetMapping("/getproducers")
  @ResponseBody
  public List<Producer> getAllProducers() {
    return shopService.getAllProducers();
  }
}
