package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.configuration.MainConfig;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {MainConfig.class}
)
class StudentServiceTest {
    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @Autowired
    private DefaultStudentService defaultStudentService;

    @BeforeEach
    void setUp(){
        scores.load();
        students.load();
        students.merge(scores.findAll());
    }

    @Test
    void getPassedStudents() {
        List<Student> passStudent = (List<Student>) defaultStudentService.getPassedStudents();
        assertEquals(passStudent.size(),2);
    }

    @Test
    void getStudentsOrderByScore() {
        List<Student> passStudent = (List<Student>) defaultStudentService.getStudentsOrderByScore();
        assertEquals(passStudent.get(0).getScore().getScore(),80);
    }
}