package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {


    @Test
    void load(){
        Scores scores = CsvScores.getInstance();
        scores.load();
        assertNotNull(scores.findAll());
    }

    @Test
    void findAll() {
        Score testTrueScore = new Score(2,80);
        Score testFalseScore = new Score(1,60);
        Scores scores = CsvScores.getInstance();
        scores.load();
        assertTrue(scores.findAll().contains(testTrueScore));
        assertFalse(scores.findAll().contains(testFalseScore));
    }
}