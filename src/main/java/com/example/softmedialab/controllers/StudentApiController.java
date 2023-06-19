package com.example.softmedialab.controllers;

import com.example.softmedialab.entity.Student;
import com.example.softmedialab.service.abstracts.StudentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Контроллер студентов", description = "Взаимодействие с данными о студентах")
public class StudentApiController {
    private final StudentService studentService;

    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }
    @Tag(name = "Получение всех студентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка")
    })
    @GetMapping("/students")
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name = "Добавление студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка")
    })
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }
    @DeleteMapping("/{id}")
    @Tag(name = "Удаление студента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удален"),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка")
    })
    public Mono<ResponseEntity<Void>> removeStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id)
                .map(r-> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    @Tag(name = "Изменение студента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно изменен"),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка")
    })
    public Mono<ResponseEntity<Student>> updateStudentById(@PathVariable Long id,
                                                            @RequestBody Student student) {

        return studentService.updateStudent(id, student)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
