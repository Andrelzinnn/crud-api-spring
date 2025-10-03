package io.github.andrelzinnn.services.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.json.patch.JsonPatchPatchConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPathException;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.andrelzinnn.services.demo.model.Service;
import io.github.andrelzinnn.services.demo.repository.ServiceRepository;
import io.github.andrelzinnn.services.demo.services.ServiceConfigs;

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

  @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
  public ResponseEntity<Service> updatePartialServiceById(@PathVariable long id, @RequestBody JsonPatch patch) {
    try {
      Service service = serviceRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
      Service servicePatched = ServiceConfigs.applyPatchToService(patch, service);
      serviceRepository.save(servicePatched);
      return ResponseEntity.ok(servicePatched);
    } catch (JsonPatchException | JsonProcessingException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } catch (ServiceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  };

  @DeleteMapping("/{id}")
  public void deleteServiceById(@PathVariable long id) {
    Service service = serviceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Service not found id" + id));

    serviceRepository.delete(service);
  }
}
