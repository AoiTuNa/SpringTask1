package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.configuration.MainConfig;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {MainConfig.class}
)
class GradeQueryServiceTest {
    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @BeforeEach
    void setUp(){
        scores.load();
        students.load();
        students.merge(scores.findAll());
    }

    @Autowired
    private GradeQueryService gradeQueryService;
    @Test
    void getScoreByStudentName() {
        List<Score> scoreList = gradeQueryService.getScoreByStudentName("A");
        assertEquals(scoreList.size(),2);
        List<Integer> scoreValues = new ArrayList<>();
        for(Score score : scoreList){
            scoreValues.add(score.getScore());
        }
        assertTrue(scoreValues.contains(30));
        assertTrue(scoreValues.contains(70));
    }

    @Test
    void getScoreByStudentSeq() {
        Score score = gradeQueryService.getScoreByStudentSeq(1);
        assertEquals(score.getScore(),30);
    }
}