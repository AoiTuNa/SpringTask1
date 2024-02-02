package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.configuration.MainConfig;
import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {MainConfig.class}
)
class DataLoadServiceTest {


    @Autowired
    private DataLoadService dataLoadService;
    @Test
    void loadAndMerge() {
        dataLoadService.loadAndMerge();
    }
}