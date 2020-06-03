package com.enigma.swipeschat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigma.swipeschat.entity.Events;

public interface EventRepository extends JpaRepository<Events, Long>{
    Events getById(Long id);
}
