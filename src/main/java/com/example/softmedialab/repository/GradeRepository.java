package com.example.softmedialab.repository;

import com.example.softmedialab.entity.Grade;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GradeRepository extends ReactiveCrudRepository<Grade, Long> {
}
