package io.github.andrelzinnn.services.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.andrelzinnn.services.demo.model.Service;
import io.github.andrelzinnn.services.demo.repository.ServiceRepository;

@RestController
@RequestMapping("/services")
public class ServiceController {
  @Autowired
  private ServiceRepository serviceRepository;

  @GetMapping
  public List<Service> getAllServices() {
    return serviceRepository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Service> getServiceById(@PathVariable long id) {
    return serviceRepository.findById(id);
  }

  @PostMapping
  public Service createService(@RequestBody Service service) {
    return serviceRepository.save(service);
  }

  @PutMapping("/{id}")
  public Service updateServiceById(@PathVariable long id, @RequestBody Service serviceDetails) {
    Service service = serviceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Service Not Found with id " + id));
    service.setName(serviceDetails.getName());
    service.setDescription(serviceDetails.getDescription());

    return serviceRepository.save(service);
  }

  @DeleteMapping("/{id}")
  public void deleteServiceById(@PathVariable long id) {
    Service service = serviceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Service not found id" + id));

    serviceRepository.delete(service);
  }
}
