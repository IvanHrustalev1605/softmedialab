package com.example.softmedialab.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.util.annotation.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Student {
    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String birthDate;
    @Nullable
    private Grade grade;

}
