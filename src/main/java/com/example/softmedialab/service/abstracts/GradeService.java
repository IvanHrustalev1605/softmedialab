package com.example.softmedialab.service.abstracts;

import com.example.softmedialab.entity.Grade;
import reactor.core.publisher.Mono;

public interface GradeService {
    Mono<Grade> updateGradeById(Long id, Grade grade);
}
