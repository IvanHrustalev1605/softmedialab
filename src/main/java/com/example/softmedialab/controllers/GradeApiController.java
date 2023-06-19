package com.example.softmedialab.controllers;

import com.example.softmedialab.entity.Grade;
import com.example.softmedialab.service.abstracts.GradeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/grade")
@Tag(name = "Оценочная ведомость")
public class GradeApiController {
    private final GradeService gradeService;

    public GradeApiController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @PutMapping("/{id}")
    @ApiResponse(description = "Изменение параметров оценки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно изменена"),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка")
    })
    public Mono<ResponseEntity<Grade>> updateStudentById(@PathVariable Long id,
                                                           @RequestBody Grade grade) {

        return gradeService.updateGradeById(id, grade)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
