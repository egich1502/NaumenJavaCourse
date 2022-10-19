package ru.murashov.naumenjavacourse.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.repositories.ProducerRepository;

@Service
public class ShopService {

  private final ProducerRepository producerRepository;

  @Autowired
  public ShopService(ProducerRepository producerRepository) {
    this.producerRepository = producerRepository;
  }

  public Producer saveProducer(String name) {
    Producer producer = new Producer(name);
    producerRepository.save(producer);
    return producer;
  }

  public List<Producer> getAllProducers() {
    List<Producer> producers = new ArrayList<>();
    producerRepository.findAll().forEach(producers::add);
    return producers;
  }

}
