package io.github.andrelzinnn.services.demo.repository;

import io.github.andrelzinnn.services.demo.model.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
