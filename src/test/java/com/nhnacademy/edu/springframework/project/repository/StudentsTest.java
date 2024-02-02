package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.configuration.MainConfig;
import com.nhnacademy.edu.springframework.project.service.Student;
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
class StudentsTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;
    @Test
    void load() {
        students.load();

        assertNotNull(students.findAll());

    }

    @Test
    void findAll() {
        Student testTrueStudent = new Student(1,"A");
        Student testFalseStudent = new Student(1,"P");
        students.load();
        assertTrue(students.findAll().contains(testTrueStudent));
        assertFalse(students.findAll().contains(testFalseStudent));
    }

    @Test
    void merge() {
        students.load();
        scores.load();
        students.merge(scores.findAll());
        int scoreTotal = 0;
        for (Student student : students.findAll()) {
            scoreTotal = scoreTotal + student.getScore().getScore();
        }
        assertEquals(scoreTotal,180);
    }
}