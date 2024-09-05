package com.aurionpro.dboperations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Column(name = "rollnumber")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollnumber;

    @Column
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column
    private int age;
}
