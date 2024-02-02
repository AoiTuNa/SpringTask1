package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    @BeforeEach
    void setUp(){
        Students students = CsvStudents.getInstance();
        Scores score = CsvScores.getInstance();
        students.load();
        score.load();
        students.merge(score.findAll());
    }
    @Test
    void getScoreByStudentName() {
        GradeQueryService gradeQueryService = new DefaultGradeQueryService();
        List<Score> scoreList = gradeQueryService.getScoreByStudentName("A");
        assertEquals(scoreList.size(),2);
        List<Integer> scoreValues = new ArrayList<>();
        for(Score score: scoreList){
            scoreValues.add(score.getScore());
        }
        assertTrue(scoreValues.contains(30));
        assertTrue(scoreValues.contains(70));
    }

    @Test
    void getScoreByStudentSeq() {
        GradeQueryService gradeQueryService = new DefaultGradeQueryService();
        Score score = gradeQueryService.getScoreByStudentSeq(1);
        assertEquals(score.getScore(),30);
    }
}