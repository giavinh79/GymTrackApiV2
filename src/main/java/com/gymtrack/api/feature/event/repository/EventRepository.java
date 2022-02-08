package com.gymtrack.api.feature.event.repository;

import com.gymtrack.api.feature.event.model.Event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> { }
