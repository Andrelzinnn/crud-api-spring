package io.github.andrelzinnn.services.demo.repository;

import io.github.andrelzinnn.services.demo.model.Service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
