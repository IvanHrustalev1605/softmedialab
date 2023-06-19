package com.example.softmedialab.service.impl;

import com.example.softmedialab.entity.Grade;
import com.example.softmedialab.exceptions.GradeNotExistException;
import com.example.softmedialab.repository.GradeRepository;
import com.example.softmedialab.service.abstracts.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    public Mono<Grade> updateGradeById(Long id, Grade grade) {
        gradeRepository.findById(id)
                .flatMap(gradeForChange -> {
                    if (gradeForChange == null) {
                        return Mono.error(GradeNotExistException::new);
                    }
                    gradeForChange.setText(grade.getText());
                    return gradeRepository.save(gradeForChange);
                });
        return null;
    }

}
