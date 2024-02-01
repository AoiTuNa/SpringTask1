package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.configuration.MainConfig;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MainConfig.class)
class StudentsTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;
    @Test
    void load() {
        students = CsvStudents.getInstance();
        students.load();
        assertNotNull(students.findAll());
    }

    @Test
    void findAll() {
        Student testTrueStudent = new Student(1,"A");
        Student testFalseStudent = new Student(1,"P");
        students = CsvStudents.getInstance();
        students.load();
        assertTrue(students.findAll().contains(testTrueStudent));
        assertFalse(students.findAll().contains(testFalseStudent));
    }

    @Test
    void merge() {
        students = CsvStudents.getInstance();
        scores = CsvScores.getInstance();
        students.load();
        scores.load();;
        students.merge(scores.findAll());
        int scoreTotal = 0;
        for (Student student : students.findAll()) {
            scoreTotal = scoreTotal + student.getScore().getScore();
        }
        assertEquals(scoreTotal,845);
    }
}