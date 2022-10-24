package com.gymtrack.api.feature.set.repository;

import com.gymtrack.api.feature.set.model.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends CrudRepository<Set, Integer> {
}
