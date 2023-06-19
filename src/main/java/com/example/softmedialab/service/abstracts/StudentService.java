package com.example.softmedialab.service.abstracts;

import com.example.softmedialab.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> getAllStudents();
    Mono<Student> addNewStudent(Student student);
    Mono<Student> deleteStudentById(Long id);
    Mono<Student> updateStudent(Long id, Student student);
}
