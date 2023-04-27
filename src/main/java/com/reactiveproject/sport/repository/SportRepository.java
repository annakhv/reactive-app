package com.reactiveproject.sport.repository;

import com.reactiveproject.sport.model.Sport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SportRepository extends ReactiveCrudRepository<Sport,Long> {
}
