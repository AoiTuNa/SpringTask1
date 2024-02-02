package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    @BeforeEach
    void setUp(){
        Students students = CsvStudents.getInstance();
        Scores score = CsvScores.getInstance();
        students.load();
        score.load();
        students.merge(score.findAll());
    }

    @Test
    void loadAndMerge() {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
    }
}