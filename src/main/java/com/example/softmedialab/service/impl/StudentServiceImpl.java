package com.example.softmedialab.service.impl;

import com.example.softmedialab.entity.Student;
import com.example.softmedialab.exceptions.StudentNotExistException;
import com.example.softmedialab.repository.StudentRepository;
import com.example.softmedialab.service.abstracts.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Mono<Student> addNewStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Mono<Student> deleteStudentById(Long id) {
       return repository.findById(id)
                .flatMap(student ->
                        repository.delete(student)
                                .then(Mono.just(student)));
    }

    @Override
    public Mono<Student> updateStudent(Long id, Student student) {
        repository.findById(id)
                .flatMap(studentForUpdate -> {
                    if (studentForUpdate == null) {
                        return Mono.error(new StudentNotExistException("Такого студента не найдено!"));
                    }
                    studentForUpdate.setName(studentForUpdate.getName());
                    studentForUpdate.setLastName(studentForUpdate.getLastName());
                    studentForUpdate.setGrade(null);
                    studentForUpdate.setBirthDate(studentForUpdate.getBirthDate());
                    return repository.save(studentForUpdate);
                });
        return null;
    }

}
