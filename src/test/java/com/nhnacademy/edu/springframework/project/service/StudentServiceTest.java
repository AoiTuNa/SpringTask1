package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @BeforeEach
    void setUp(){
        Students students = CsvStudents.getInstance();
        Scores score = CsvScores.getInstance();
        students.load();
        score.load();
        students.merge(score.findAll());
    }
    @Test
    void getPassedStudents() {
        StudentService studentService = new DefaultStudentService();
        List<Student> passStudent = (List<Student>) studentService.getPassedStudents();
        assertEquals(passStudent.size(),2);
    }

    @Test
    void getStudentsOrderByScore() {
        StudentService studentService = new DefaultStudentService();
        List<Student> passStudent = (List<Student>) studentService.getStudentsOrderByScore();
        assertEquals(passStudent.get(0).getScore().getScore(),80);
    }
}