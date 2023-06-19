package com.example.softmedialab.controllers;

import com.example.softmedialab.entity.Grade;
import com.example.softmedialab.service.abstracts.GradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/grade")
public class GradeApiController {
    private final GradeService gradeService;

    public GradeApiController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Изменение студента по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно изменен"),
            @ApiResponse(code = 400, message = "Произошла ошибка")
    })
    public Mono<ResponseEntity<Grade>> updateStudentById(@PathVariable Long id,
                                                           @RequestBody Grade grade) {

        return gradeService.updateGradeById(id, grade)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
