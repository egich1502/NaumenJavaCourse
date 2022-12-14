package ru.murashov.naumenjavacourse.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.repositories.ProducerRepository;

@Service
public class ProducerService {

  private final ProducerRepository producerRepository;

  @Autowired
  public ProducerService(ProducerRepository producerRepository) {
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


    public Producer getProducer(int id){
      Producer producer = producerRepository.findById(id).get();
      return producer;
    }

    public void deleteProducer(int id){
      producerRepository.deleteById(id);
    }

    public void updateProducer(int id, Producer updatedProducer){
      Producer producerToBeUpdated = producerRepository.findById(id).get();
      producerToBeUpdated.setName(updatedProducer.getName());
    }
}
