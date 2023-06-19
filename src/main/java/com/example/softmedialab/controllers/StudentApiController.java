package com.example.softmedialab.controllers;

import com.example.softmedialab.entity.Student;
import com.example.softmedialab.service.abstracts.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/student")
@Api(value = "Контроллер студентов", description = "Взаимодействие с данными о студентах")
public class StudentApiController {
    private final StudentService studentService;

    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }
    @ApiOperation(value = "Получение всех студентов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение данных"),
            @ApiResponse(code = 400, message = "Произошла ошибка")
    })
    @GetMapping("/students")
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавление студента")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно добавлен"),
            @ApiResponse(code = 400, message = "Произошла ошибка")
    })
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаление студента по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно удален"),
            @ApiResponse(code = 400, message = "Произошла ошибка")
    })
    public Mono<ResponseEntity<Void>> removeStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id)
                .map(r-> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Изменение студента по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно изменен"),
            @ApiResponse(code = 400, message = "Произошла ошибка")
    })
    public Mono<ResponseEntity<Student>> updateStudentById(@PathVariable Long id,
                                                            @RequestBody Student student) {

        return studentService.updateStudent(id, student)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
